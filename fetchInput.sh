#!/bin/bash

SESSION=$(< .session)
DAY=$1
PADDED_DAY=$(printf "%02d" $DAY)
BASE_DIR=src/test/resources

if [ ! -d  $BASE_DIR/day$PADDED_DAY ]; then
  mkdir -p $BASE_DIR/day$PADDED_DAY
  touch $BASE_DIR/day$PADDED_DAY/sample.txt
  curl -v https://adventofcode.com/2021/day/$DAY/input --cookie "session=$SESSION" > $BASE_DIR/day$PADDED_DAY/input.txt

  cat src/test/resources/DayTemplate.ktt | sed -e "s/\$PADDED_DAY/$PADDED_DAY/g" | sed -e "s/\$DAY/$DAY/g" > src/main/kotlin/day${PADDED_DAY}.kt
  cat src/test/resources/DayTestTemplate.ktt | sed -e "s/\$PADDED_DAY/$PADDED_DAY/g" | sed -e "s/\$DAY/$DAY/g" > src/test/kotlin/Day${PADDED_DAY}Test.kt
else
  echo "$BASE_DIR/day$PADDED_DAY already exists"
  exit 1
fi