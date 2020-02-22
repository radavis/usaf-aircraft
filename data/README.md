# usaf-aircraft

Demostrates how to read data from an HTML table, and export that data to CSV.

## Retrieving General USAF Aircraft Information

```bash
$ curl -o wiki-usaf-aircraft.html https://en.wikipedia.org/wiki/List_of_active_United_States_Air_Force_aircraft
$ ./html2csv usaf-aircraft-wikipedia.html > usaf-aircraft.csv
```

## Retrieving Images

```bash
$ ./get-aircraft-data usaf-aircraft.csv > usaf-aircraft-\[2\].csv
```