package com.alonsoaliaga.checkdateexpansion.utils;

import org.bukkit.ChatColor;

import javax.annotation.Nonnull;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatUtils {
    private static final Pattern HEX_FORMAT_TO_PARSE = Pattern.compile("(&?#[a-f0-9]{6})",Pattern.CASE_INSENSITIVE);

    public static String parseAllFormatting(@Nonnull String string) {
        Matcher matcher = HEX_FORMAT_TO_PARSE.matcher(string);
        while(matcher.find()) {
            String match = matcher.group(0);
            string = string.replace(match,hexToParsedHex(match));
        }
        return ChatColor.translateAlternateColorCodes('&',string);
    }
    private static String hexToParsedHex(String hex) {
        if(hex.length() >= 7) {
            StringBuilder result = new StringBuilder("§x");
            hex = hex.substring(hex.length() - 6);
            for (char c : hex.toCharArray()) {
                result.append("§").append(c);
            }
            return result.toString();
        }
        return hex;
    }
}