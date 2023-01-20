# CheckDate-Expansion
This is a [PlaceholderAPI](https://links.alonsoaliaga.com/PlaceholderAPI) expansion to allow owners/configurator check dates with custom output.

# Installation
You can install this expansion in 2 ways:
### 1) PlaceholderAPI eCloud (Available ✔️)
While being in console or having OP run the following commands:
> /papi ecloud download checkdate\
> /papi reload

✅ Expansion is ready to be used!
### 2) Manual download
Go to [eCloud](https://api.extendedclip.com/expansions/checkdate/) and click `Download Latest` button to get the .jar file.\
Copy and paste the file in `/plugins/PlaceholderAPI/expansions/` and run:
> /papi reload

✅ Expansion is ready to be used!
# Placeholders
The following placeholders are available:
> ###   %checkdate_isbetweendaysnumbers_day1,day2_Yes output_No output%
> Allows you to return custom message if current server/machine day is <br>
> between the provided min and max days. <br>
> Supports PlaceholderAPI in Yes/No output but requires `{ }` instead of `% %`.<br>
>
> **Example:** %checkdate_isbetweendaysnumbers_5,10_&2✓_&c✘%<br>
> **Output:** If server/machine date is between 5 and 10 it will return `&2✓`, otherwise it will return `&c✘`.

> ###   %checkdate_isbetween_date1,date2_Yes output_No output%
> Allows you to return custom message if current server/machine "month/day" is <br>
> between the provided dates. Year is ignored, only month and day are considered. <br>
> Supports PlaceholderAPI in Yes/No output but requires `{ }` instead of `% %`.<br>
>
> **Example:** %checkdate_isbetween_2/1,2/28_&2✓_&c✘%<br>
> **Output:** If server/machine date is between february 1st(00:00:00) and 28th(23:59:59) it will return `&2✓`, otherwise it will return `&c✘`.

> ###   %checkdate_isbetweenwithyear_date1,date2_Yes output_No output%
> Allows you to return custom message if current server/machine "month/day/year" is <br>
> between the provided dates. Year is NOT ignored, full day is considered. <br>
> Supports PlaceholderAPI in Yes/No output but requires `{ }` instead of `% %`.<br>
>
> **Example:** %checkdate_isbetween_2/1/2023,3/1/2023_&2✓_&c✘%<br>
> **Output:** If server/machine date is between february 1st(00:00:00) and 28th(23:59:59) in 2023 it will return `&2✓`, otherwise it will return `&c✘`.

> ###   %checkdate_isbetweenhours_hour1,hour2_Yes output_No output%
> Allows you to return custom message if current server/machine "hour" is <br>
> between the provided hours. Month, day, year, minutes and seconds are ignored. <br>
> Supports PlaceholderAPI in Yes/No output but requires `{ }` instead of `% %`.<br>
>
> **Example:** %checkdate_isbetweenhours_12,18_&2✓_&c✘%<br>
> **Output:** If server/machine hour is between 12:00:00 and 18:00:00 it will return `&2✓`, otherwise it will return `&c✘`.

> ###   %checkdate_isbetweenhoursminutes_hour1,hour2_Yes output_No output%
> Allows you to return custom message if current server/machine "hour:minute" is <br>
> between the provided hours. Month, day, year and seconds are ignored. <br>
> Supports PlaceholderAPI in Yes/No output but requires `{ }` instead of `% %`.<br>
>
> **Example:** %checkdate_isbetweenhoursminutes_12:30,18:30_&2✓_&c✘%<br>
> **Output:** If server/machine hour is between 12:30:00 and 18:30:00 it will return `&2✓`, otherwise it will return `&c✘`.

> ###   %checkdate_isbetweenhoursminutesseconds_hour1,hour2_Yes output_No output%
> Allows you to return custom message if current server/machine "hour:minute:seconds" is <br>
> between the provided hours. Month, day and year are ignored. <br>
> Supports PlaceholderAPI in Yes/No output but requires `{ }` instead of `% %`.<br>
>
> **Example:** %checkdate_isbetweenhoursminutes_12:30:15,18:30:15_&2✓_&c✘%<br>
> **Output:** If server/machine hour is between 12:30:15 and 18:30:15 it will return `&2✓`, otherwise it will return `&c✘`.

> ###   %checkdate_isdate_date1,date2,date3,date4,date5_Yes output_No output%
> Allows you to return custom message if current server/machine "month/day" is <br>
> included in the provided list. Year is ignored. <br>
> Supports PlaceholderAPI in Yes/No output but requires `{ }` instead of `% %`.<br>
>
> **Example:** %checkdate_isdate_1/1,2/14,5/1,12/25_&2✓_&c✘%<br>
> **Output:** If server/machine date is Jan 1st, Feb 14th, May 1st or Dec 25 it will return `&2✓`, otherwise it will return `&c✘`.

> ###   %checkdate_isdaynumber_day1,day2,day3,day4,dayN_Yes output_No output%
> Allows you to return custom message if current server/machine "day" is <br>
> included in the provided list. Month and year are ignored. <br>
> Supports PlaceholderAPI in Yes/No output but requires `{ }` instead of `% %`.<br>
>
> **Example:** %checkdate_isdaynumber_10,20,30_&2✓_&c✘%<br>
> **Output:** If server/machine day is 10, 20 or 30 it will return `&2✓`, otherwise it will return `&c✘`.

> ###   %checkdate_isdayname_dayname1,dayname2,dayname3,daynameN_Yes output_No output%
> Allows you to return custom message if current server/machine "day" is <br>
> included in the provided list. Month and year are ignored. <br>
> Supports PlaceholderAPI in Yes/No output but requires `{ }` instead of `% %`.<br>
>
> **Example:** %checkdate_isdaynumber_monday,wednesday,sunday_&2✓_&c✘%<br>
> **Output:** If server/machine day is monday, wednesday or sunday it will return `&2✓`, otherwise it will return `&c✘`.

> ###   %checkdate_dayname%
> Returns the day of the server/machine. Output can be customized in config.yml file.<br>
>
> **Example:** %checkdate_dayname%<br>
> **Output:** Returns Monday, Tuesday, Wednesday, etc..

# Want more cool and useful expansions?
<p align="center">
    <a href="https://alonsoaliaga.com/moregradients">MoreGradients Expansion</a><br>
    Customize texts a bit more with cool gradient styles, support custom and iridium!<br>
    <br>
    <a href="https://alonsoaliaga.com/capitalize">Capitalize Expansion</a><br>
    Customize texts a bit more removing underscores, dashes and capitalizing letters!<br>
    <br>
    <a href="https://alonsoaliaga.com/checkmoney">CheckMoney Expansion</a><br>
    Check if player has enough funds or not with custom output! (Specially for menu plugins)<br>
</p>

# Want more tools?
**Make sure to check our [BRAND NEW TOOL](https://alonsoaliaga.com/hex) to generate your own text with gradients!**<br>
<p align="center">
    <a href="https://alonsoaliaga.com/hex"><img src="https://i.imgur.com/766Es8I.png" alt="Our brand new tool!"/></a>
</p>

# Do you have a cool expansion idea?
<p align="center">
    <a href="https://alonsoaliaga.com/discord">Join us on Discord</a><br>
    <a href="https://alonsoaliaga.com/discord"><img src="https://i.imgur.com/2pslxIN.png"></a><br>
    Let us know what's your idea and it might become true!
</p>

# Questions?
<p align="center">
    <a href="https://alonsoaliaga.com/discord"><img style="width:200px;" src="https://i.imgur.com/laEFHcG.gif"></a><br>
    <a href="https://alonsoaliaga.com/discord"><span style="font-size:25px;font-weight:bold;color:rgb(100,100,255);">Join us in our discord!</span></a>
</p>