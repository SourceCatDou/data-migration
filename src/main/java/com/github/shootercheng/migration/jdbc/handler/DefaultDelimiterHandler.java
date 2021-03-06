package com.github.shootercheng.migration.jdbc.handler;

import com.github.shootercheng.migration.jdbc.MySqlScriptRunner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * comment marked delimiter
 *
 * @author James
 */
public class DefaultDelimiterHandler implements DelimiterHandler {
    public static final Pattern DELIMITER_PATTERN = Pattern.compile("^\\s*((--)|(//))?\\s*(//)?\\s*@?DELIMITER\\s+([^\\s]+)", Pattern.CASE_INSENSITIVE);

    @Override
    public boolean resetDelimiter(MySqlScriptRunner scriptRunner, String trimmedLine, StringBuilder commond) {
        if (lineIsDelimiterMark(trimmedLine)) {
            Matcher matcher = DELIMITER_PATTERN.matcher(trimmedLine);
            if (matcher.find()) {
                scriptRunner.setDelimiter(matcher.group(5));
                return true;
            }
        }
        return false;
    }

    private boolean lineIsDelimiterMark(String trimmedLine) {
        return trimmedLine.startsWith("//") || trimmedLine.startsWith("--");
    }
}
