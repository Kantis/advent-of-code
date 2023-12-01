#!/bin/bash

YEAR=2023
SESSION=$(< .session)
DAY=$1
PADDED_DAY=$(printf "%02d" $DAY)
BASE_DIR=$YEAR/src/test/resources

if [ ! -d  $BASE_DIR/day$PADDED_DAY ]; then
  mkdir -p $BASE_DIR/day$PADDED_DAY
  touch $BASE_DIR/day$PADDED_DAY/sample.txt
  curl -v https://adventofcode.com/$YEAR/day/$DAY/input --cookie "session=$SESSION" > $BASE_DIR/day$PADDED_DAY/input.txt

  cat template/DayTemplate.ktt | sed -e "s/\$PADDED_DAY/$PADDED_DAY/g" | sed -e "s/\$DAY/$DAY/g" > $YEAR/src/main/kotlin/day${PADDED_DAY}.kt
  cat template/DayTestTemplate.ktt | sed -e "s/\$PADDED_DAY/$PADDED_DAY/g" | sed -e "s/\$DAY/$DAY/g" > $YEAR/src/test/kotlin/Day${PADDED_DAY}Test.kt
else
  echo "$BASE_DIR/day$PAED_DAY already exists"
  exit 1
fi
