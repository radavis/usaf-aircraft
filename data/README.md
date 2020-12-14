# usaf-aircraft

Demonstrates how to read data from an HTML table, and export that data to CSV.

## Retrieving General USAF Aircraft Information

```bash
$ curl -o wiki-usaf-aircraft.html https://en.wikipedia.org/wiki/List_of_active_United_States_Air_Force_aircraft
$ ./html2csv usaf-aircraft-wikipedia.html > usaf-aircraft.csv
```

## Retrieve Aircraft Data

```bash
$ ./get-aircraft-data usaf-aircraft.csv > usaf-aircraft-\[2\].csv
```

## Extract Categories

```bash
$ irb
irb(main)> require "csv"
irb(main)> rows = CSV.read("usaf-aircraft.csv", { headers: true, col_sep: ";" })
irb(main)> categories = rows.map { |row| row["Category"] }.uniq
irb(main)> puts categories
irb(main)> exit
```

## Extract Manufacturers

```bash
$ irb
irb(main)> require "csv"
irb(main)> rows = CSV.read("usaf-aircraft-[2].csv", { headers: true, col_sep: ";" })
irb(main)> manufacturers = rows.map { |row| row["Manufacturer"] }.uniq.sort
irb(main)> puts manufacturers
irb(main)> exit
```
