package com.alonsoaliaga.checkdateexpansion;

import com.alonsoaliaga.checkdateexpansion.utils.ChatUtils;
import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.Cacheable;
import me.clip.placeholderapi.expansion.Configurable;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class CheckDateExpansion extends PlaceholderExpansion implements Configurable, Cacheable {
    private boolean debug = false;
    private final Pattern splitPattern = Pattern.compile("_(?=[^\\}]*(?:\\{|$))");
    private final SimpleDateFormat monthDayYearHourMinuteSecondFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss",Locale.US);
    private final SimpleDateFormat monthDayYearHourMinuteFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm",Locale.US);
    private final SimpleDateFormat monthDayYearHourFormat = new SimpleDateFormat("MM/dd/yyyy HH",Locale.US);
    private final SimpleDateFormat monthDayYearFormat = new SimpleDateFormat("MM/dd/yyyy",Locale.US);
    private final SimpleDateFormat monthDayFormat = new SimpleDateFormat("MM/dd",Locale.US);
    private final SimpleDateFormat dayNameFormat = new SimpleDateFormat("EEEE",Locale.US);
    private final HashMap<String,String> dayNameMap = new HashMap<>();
    private final String unknownName;
    public CheckDateExpansion() {
        try {
            debug = getPlaceholderAPI().getPlaceholderAPIConfig().isDebugMode();
        } catch (Throwable ignored) {}
        dayNameMap.put("Monday", ChatUtils.parseAllFormatting(getString("days.monday","Monday")));
        dayNameMap.put("Tuesday",ChatUtils.parseAllFormatting(getString("days.tuesday","Tuesday")));
        dayNameMap.put("Wednesday",ChatUtils.parseAllFormatting(getString("days.wednesday","Wednesday")));
        dayNameMap.put("Thursday",ChatUtils.parseAllFormatting(getString("days.thursday","Thursday")));
        dayNameMap.put("Friday",ChatUtils.parseAllFormatting(getString("days.friday","Friday")));
        dayNameMap.put("Saturday",ChatUtils.parseAllFormatting(getString("days.saturday","Saturday")));
        dayNameMap.put("Sunday",ChatUtils.parseAllFormatting(getString("days.sunday","Sunday")));
        unknownName = ChatUtils.parseAllFormatting(getString("days.unknown","Unknown?"));
    }
    @Override
    public void clear() {
        dayNameMap.clear();
    }
    @Override
    public String onPlaceholderRequest(Player p, String params) {
        if (params.equalsIgnoreCase("version")) {
            return getVersion();
        }
        if (params.equalsIgnoreCase("author")) {
            return getAuthor();
        }
        if (params.startsWith("isbetweendaysnumbers_")) { // %checkdate_isbetweendaysnumbers_day1,day2_Yes output_No output%
            String[] parts = splitPattern.split(params.substring(21));
            if(parts.length >= 3) {
                String[] days = parts[0].split(",");
                int dayOfMonth = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
                try{
                    int first = Integer.parseInt(days[0]);
                    int second = Integer.parseInt(days[1]);
                    if(first >= dayOfMonth && dayOfMonth <= second) {
                        return ChatUtils.parseAllFormatting(PlaceholderAPI.setBracketPlaceholders(p,parts[1]
                                .replace("[DAY]",String.valueOf(dayOfMonth))));
                    }else{
                        return ChatUtils.parseAllFormatting(PlaceholderAPI.setBracketPlaceholders(p,parts[2]
                                .replace("[DAY]",String.valueOf(dayOfMonth))));
                    }
                }catch (Throwable e) {
                    if(debug) Bukkit.getServer().getConsoleSender().sendMessage("[CheckMoney-Expansion] Debug mode is enabled. Error: "+e.getMessage());
                    return ChatUtils.parseAllFormatting(PlaceholderAPI.setBracketPlaceholders(p,parts[2]
                            .replace("[DAY]",String.valueOf(dayOfMonth))));
                }
            }
            return null;
        }
        if (params.startsWith("isbetween_")) { // %checkdate_isbetween_date1,date2_Yes output_No output%
            String[] parts = splitPattern.split(params.substring(10));
            if(parts.length >= 3) {
                Date current = new Date();
                int year = Calendar.getInstance().get(Calendar.YEAR);
                String[] dates = parts[0].toLowerCase(Locale.ROOT).split(",");
                if(dates.length >= 2) {
                    try {
                        Date date1 = monthDayYearFormat.parse(dates[0]+"/"+year);
                        Date date2 = monthDayYearHourMinuteSecondFormat.parse(dates[1]+"/"+year+" 23:59:59");
                        if(isBetween(current,date1,date2)) {
                            return ChatUtils.parseAllFormatting(PlaceholderAPI.setBracketPlaceholders(p, parts[1]
                                    .replace("[CURRENT-DATE]", monthDayFormat.format(current))));
                        }else{
                            return ChatUtils.parseAllFormatting(PlaceholderAPI.setBracketPlaceholders(p, parts[2]
                                    .replace("[CURRENT-DATE]", monthDayFormat.format(current))));
                        }
                    }catch (Throwable e) {
                        return ChatUtils.parseAllFormatting(PlaceholderAPI.setBracketPlaceholders(p, parts[2]
                                .replace("[CURRENT-DATE]", monthDayFormat.format(current))));
                    }
                }
            }
            return null;
        }
        if (params.startsWith("isbetweenwithyear_")) { // %checkdate_isbetweenwithyear_date1,date2_Yes output_No output%
            String[] parts = splitPattern.split(params.substring(18));
            if(parts.length >= 3) {
                Date current = new Date();
                String[] dates = parts[0].toLowerCase(Locale.ROOT).split(",");
                if(dates.length >= 2) {
                    try {
                        Date date1 = monthDayYearFormat.parse(dates[0]);
                        Date date2 = monthDayYearHourMinuteSecondFormat.parse(dates[1]+" 23:59:59");
                        if(isBetween(current,date1,date2)) {
                            return ChatUtils.parseAllFormatting(PlaceholderAPI.setBracketPlaceholders(p, parts[1]
                                    .replace("[CURRENT-DATE]", monthDayFormat.format(current))));
                        }else{
                            return ChatUtils.parseAllFormatting(PlaceholderAPI.setBracketPlaceholders(p, parts[2]
                                    .replace("[CURRENT-DATE]", monthDayFormat.format(current))));
                        }
                    }catch (Throwable e) {
                        return ChatUtils.parseAllFormatting(PlaceholderAPI.setBracketPlaceholders(p, parts[2]
                                .replace("[CURRENT-DATE]", monthDayFormat.format(current))));
                    }
                }
            }
            return null;
        }
        if (params.startsWith("isbetweenhours_")) { // %checkdate_isbetweenhours_hour1,hour2_Yes output_No output%
            String[] parts = splitPattern.split(params.substring(15));
            if(parts.length >= 3) {
                Date current = new Date();
                String[] hours = parts[0].toLowerCase(Locale.ROOT).split(",");
                if(hours.length >= 2) {
                    try {
                        String first = monthDayYearFormat.format(current);
                        Date date1 = monthDayYearHourFormat.parse(first+" "+hours[0]);
                        Date date2 = monthDayYearHourFormat.parse(first+" "+hours[1]);
                        if(isBetween(current,date1,date2)) {
                            return ChatUtils.parseAllFormatting(PlaceholderAPI.setBracketPlaceholders(p, parts[1]
                                    .replace("[CURRENT-DATE]", monthDayYearHourMinuteSecondFormat.format(current))));
                        }else{
                            return ChatUtils.parseAllFormatting(PlaceholderAPI.setBracketPlaceholders(p, parts[2]
                                    .replace("[CURRENT-DATE]", monthDayYearHourMinuteSecondFormat.format(current))));
                        }
                    }catch (Throwable e) {
                        return ChatUtils.parseAllFormatting(PlaceholderAPI.setBracketPlaceholders(p, parts[2]
                                .replace("[CURRENT-DATE]", monthDayYearHourMinuteSecondFormat.format(current))));
                    }
                }
            }
            return null;
        }
        if (params.startsWith("isbetweenhoursminutes_")) { // %checkdate_isbetweenhoursminutes_hour1,hour2_Yes output_No output%
            String[] parts = splitPattern.split(params.substring(22));
            if(parts.length >= 3) {
                Date current = new Date();
                String[] hours = parts[0].toLowerCase(Locale.ROOT).split(",");
                if(hours.length >= 2) {
                    try {
                        String first = monthDayYearFormat.format(current);
                        Date date1 = monthDayYearHourMinuteFormat.parse(first+" "+hours[0]);
                        Date date2 = monthDayYearHourMinuteFormat.parse(first+" "+hours[1]);
                        if(isBetween(current,date1,date2)) {
                            return ChatUtils.parseAllFormatting(PlaceholderAPI.setBracketPlaceholders(p, parts[1]
                                    .replace("[CURRENT-DATE]", monthDayYearHourMinuteSecondFormat.format(current))));
                        }else{
                            return ChatUtils.parseAllFormatting(PlaceholderAPI.setBracketPlaceholders(p, parts[2]
                                    .replace("[CURRENT-DATE]", monthDayYearHourMinuteSecondFormat.format(current))));
                        }
                    }catch (Throwable e) {
                        return ChatUtils.parseAllFormatting(PlaceholderAPI.setBracketPlaceholders(p, parts[2]
                                .replace("[CURRENT-DATE]", monthDayYearHourMinuteSecondFormat.format(current))));
                    }
                }
            }
            return null;
        }
        if (params.startsWith("isbetweenhoursminutesseconds_")) { // %checkdate_isbetweenhoursminutesseconds_hour1,hour2_Yes output_No output%
            String[] parts = splitPattern.split(params.substring(29));
            if(parts.length >= 3) {
                Date current = new Date();
                String[] hours = parts[0].toLowerCase(Locale.ROOT).split(",");
                if(hours.length >= 2) {
                    try {
                        String first = monthDayYearFormat.format(current);
                        Date date1 = monthDayYearHourMinuteSecondFormat.parse(first+" "+hours[0]);
                        Date date2 = monthDayYearHourMinuteSecondFormat.parse(first+" "+hours[1]);
                        if(isBetween(current,date1,date2)) {
                            return ChatUtils.parseAllFormatting(PlaceholderAPI.setBracketPlaceholders(p, parts[1]
                                    .replace("[CURRENT-DATE]", monthDayYearHourMinuteSecondFormat.format(current))));
                        }else{
                            return ChatUtils.parseAllFormatting(PlaceholderAPI.setBracketPlaceholders(p, parts[2]
                                    .replace("[CURRENT-DATE]", monthDayYearHourMinuteSecondFormat.format(current))));
                        }
                    }catch (Throwable e) {
                        return ChatUtils.parseAllFormatting(PlaceholderAPI.setBracketPlaceholders(p, parts[2]
                                .replace("[CURRENT-DATE]", monthDayYearHourMinuteSecondFormat.format(current))));
                    }
                }
            }
            return null;
        }
        if (params.startsWith("isdate_")) { // %checkdate_isdate_date1,date2,date3,date4,date5_Yes output_No output%
            String[] parts = splitPattern.split(params.substring(7));
            if(parts.length >= 3) {
                String current = monthDayFormat.format(new Date());
                try {
                    for (String dString : parts[0].toLowerCase(Locale.ROOT).split(",")) {
                        if(dString.equals(current)) {
                            return ChatUtils.parseAllFormatting(PlaceholderAPI.setBracketPlaceholders(p, parts[1]
                                    .replace("[CURRENT-DATE]", current)));
                        }
                    }
                    return ChatUtils.parseAllFormatting(PlaceholderAPI.setBracketPlaceholders(p, parts[2]
                            .replace("[CURRENT-DATE]", current)));
                }catch (Throwable e) {
                    return ChatUtils.parseAllFormatting(PlaceholderAPI.setBracketPlaceholders(p, parts[2]
                            .replace("[CURRENT-DATE]", current)));
                }
            }
        }
        if (params.startsWith("isdaynumber_")) { // %checkdate_isdaynumber_10,20,30_Yes output_No output%
            String[] parts = splitPattern.split(params.substring(12));
            if(parts.length >= 3) {
                int dayOfMonth = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
                for (String s : parts[0].split(",")) {
                    try{
                        if(dayOfMonth == Integer.parseInt(s)) {
                            return ChatUtils.parseAllFormatting(PlaceholderAPI.setBracketPlaceholders(p,parts[1]
                                    .replace("[DAY]",String.valueOf(dayOfMonth))));
                        }
                    }catch (Throwable ignored){}
                }
                return ChatUtils.parseAllFormatting(PlaceholderAPI.setBracketPlaceholders(p,parts[2]
                        .replace("[DAY]",String.valueOf(dayOfMonth))));
            }
        }
        if (params.startsWith("isdayname_")) { // %checkdate_isdayname_monday,wednesday,sunday_Yes output_No output%
            String[] parts = splitPattern.split(params.substring(10));
            if(parts.length >= 3) {
                List<String> daysToMatch = Arrays.asList(parts[0].toLowerCase(Locale.ROOT).split(","));
                String name = dayNameFormat.format(new Date());
                if(daysToMatch.contains(name.toLowerCase(Locale.ROOT))) {
                    return ChatUtils.parseAllFormatting(PlaceholderAPI.setBracketPlaceholders(p,parts[1]
                            .replace("[ORIGINAL-DAY-NAME]",name)
                            .replace("[DAY-NAME]",dayNameMap.getOrDefault(name,unknownName))));
                }else{
                    return ChatUtils.parseAllFormatting(PlaceholderAPI.setBracketPlaceholders(p,parts[2]
                            .replace("[ORIGINAL-DAY-NAME]",name)
                            .replace("[DAY-NAME]",dayNameMap.getOrDefault(name,unknownName))));
                }
            }
            return null;
        }
        if (params.equalsIgnoreCase("dayname")) { // %checkdate_dayname%
            String name = dayNameFormat.format(new Date());
            if(debug) {
                Bukkit.getServer().getConsoleSender().sendMessage("[CheckDate-Expansion] (Debug) Dayname is: '"+name+"'. Contained in map? "+
                        dayNameMap.containsKey(name)+". Value: "+dayNameMap.getOrDefault(name,unknownName));
            }
            return dayNameMap.getOrDefault(name,unknownName);
        }
        return null;
    }
    private boolean isBetween(Date toCheck, Date date1, Date date2) {
        if(debug) {
            Bukkit.getServer().getConsoleSender().sendMessage("[CheckDate-Expansion] (Debug) Checking if between: '"+monthDayYearHourMinuteSecondFormat.format(toCheck)+"'. Date1: '"+monthDayYearHourMinuteSecondFormat.format(date1)+"'. Date2: '"+monthDayYearHourMinuteSecondFormat.format(date2)+"'");
        }
        return !toCheck.before(date1) && !toCheck.after(date2);
    }
    @Override
    public Map<String, Object> getDefaults() {
        final Map<String, Object> defaults = new LinkedHashMap<>();
        defaults.put("days.monday","Monday");
        defaults.put("days.tuesday","Tuesday");
        defaults.put("days.wednesday","Wednesday");
        defaults.put("days.thursday","Thursday");
        defaults.put("days.friday","Friday");
        defaults.put("days.saturday","Saturday");
        defaults.put("days.sunday","Sunday");
        defaults.put("days.unknown","Unknown?");
        return defaults;
    }
    @Override
    public @NotNull String getIdentifier() {
        return "checkdate";
    }
    @Override
    public @NotNull String getAuthor() {
        return "AlonsoAliaga";
    }
    @Override
    public @NotNull String getVersion() {
        return "0.1-BETA";
    }
}