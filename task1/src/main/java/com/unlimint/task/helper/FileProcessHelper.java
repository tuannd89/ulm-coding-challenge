package com.unlimint.task.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.bean.CsvToBeanBuilder;
import com.unlimint.task.bean.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@Component
public class FileProcessHelper {
    private static final String EXTENSION_CSV = ".csv";
    private static final String EXTENSION_JSON = ".json";
    private static final String MESSAGE_DONE = "OK";
    private static final String MESSAGE_EMPTY_LINE = "Empty Line";
    private static final String MESSAGE_EMPTY_SPLIT_TEXT = " - ";
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // process and print result
    public void processFile(String pathname) {
        if (pathname.contains(EXTENSION_CSV)) {
            readFile(pathname).parallelStream()
                    .forEach(line -> printOutputIfAny(parseOrderFromCsv(line)));
        } else if (pathname.contains(EXTENSION_JSON)) {
            readFile(pathname).parallelStream()
                    .forEach(line -> printOutputIfAny(parseOrderFromJson(line)));
        }
    }

    private List<FileLine> readFile(String pathname) {
        List<FileLine> result = new ArrayList<>();
        try {
            File file = new File(pathname);
            Stream<String> text = Files.lines(file.toPath());
            AtomicInteger index = new AtomicInteger(1);
            text.forEach(line -> {
                result.add(new FileLine(index, pathname, line));
                index.getAndIncrement();
            });
        } catch (IOException e) {
            //e.printStackTrace();
        }
        return result;
    }

    private OrderResultDto parseOrderFromCsv(FileLine line) {
        if (StringUtils.isBlank(line.getText())) {
            return buildResultFromEmptyLine(line.getIndex());
        }
        StringReader reader = null;
        OrderResultDto result;
        try {
            reader = new StringReader(line.getText());
            // create csv bean reader
            List<OrderCSVToBean> orders = new CsvToBeanBuilder(reader)
                    .withType(OrderCSVToBean.class)
                    .build().parse();
            OrderCSVToBean order = orders.get(0);
            result = buildResultFromOrder(line, order);
        } catch (Exception e) {
            //e.printStackTrace();
            return buildResultFromMessage(line.getIndex(), e.getMessage());
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return result;
    }

    private OrderResultDto parseOrderFromJson(FileLine line) {
        if (StringUtils.isBlank(line.getText())) {
            return buildResultFromEmptyLine(line.getIndex());
        }
        OrderResultDto result;
        try {
            OrderCSVToBean order = MAPPER.readValue(line.getText(), OrderCSVToBean.class);
            result = buildResultFromOrder(line, order);
        } catch (Exception e) {
            //e.printStackTrace();
            return buildResultFromMessage(line.getIndex(), e.getMessage());
        }
        return result;
    }

    public void printOutputIfAny(OrderResultDto data) {
        if (data != null) {
            try {
                System.out.println(MAPPER.writeValueAsString(data));
            } catch (JsonProcessingException e) {
                //e.printStackTrace();
            }
        }
    }

    private OrderResultDto buildResultFromOrder(FileLine line, OrderCSVToBean order) {
        String resultMessage = validate(order);
        if (StringUtils.isEmpty(resultMessage)) {
            resultMessage = MESSAGE_DONE;
            return new OrderParsedResultDto(Integer.parseInt(order.getOrderId()),
                    new BigDecimal(order.getAmount()),
                    order.getComment(),
                    line.getFileName(),
                    line.getIndex(),
                    resultMessage);
        } else {
            return new OrderInvalidResultDto(order.getOrderId(),
                    order.getAmount(),
                    order.getComment(),
                    line.getFileName(),
                    line.getIndex(),
                    resultMessage);
        }
    }

    private OrderParsedResultDto buildResultFromEmptyLine(int line) {
        return buildResultFromMessage(line, MESSAGE_EMPTY_LINE);
    }

    private OrderParsedResultDto buildResultFromMessage(int line, String message) {
        OrderParsedResultDto result = new OrderParsedResultDto();
        result.setLine(line);
        result.setResult(message);
        return result;
    }

    private String validate(OrderCSVToBean input) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<OrderCSVToBean>> violations = validator.validate(input);
        StringBuilder builder = new StringBuilder();
        violations.forEach(x -> {
            builder.append(x.getPropertyPath().toString());
            builder.append(MESSAGE_EMPTY_SPLIT_TEXT);
            builder.append(x.getMessage());
        });
        return builder.toString();
    }
}
