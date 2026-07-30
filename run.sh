#!/bin/bash
if [ -z "$1" ]; then
    echo "Usage: ./run.sh <program_number (1-15)>"
    exit 1
fi

PROG=$1
case $PROG in
    1) CLASS="StudentAverage"; IN="input1.txt" ;;
    2) CLASS="DepartmentSalary"; IN="input2.txt" ;;
    3) CLASS="StudentAttendance"; IN="input3.txt" ;;
    4) CLASS="EvenOddCount"; IN="input4.txt" ;;
    5) CLASS="MovieRating"; IN="input5.txt" ;;
    6) CLASS="HighestMark"; IN="input6.txt" ;;
    7) CLASS="LongestWord"; IN="input7.txt" ;;
    8) CLASS="LogLevelCount"; IN="input8.txt" ;;
    9) CLASS="MaxAvgTemp"; IN="input9.txt" ;;
    10) CLASS="GPASort"; IN="input10.txt" ;;
    11) CLASS="ReverseLine"; IN="input11.txt" ;;
    12) CLASS="UppercaseLine"; IN="input12.txt" ;;
    13) CLASS="CountDigits"; IN="input13.txt" ;;
    14) CLASS="RemoveDuplicates"; IN="input14.txt" ;;
    15) CLASS="SecondHighestSalary"; IN="input15.txt" ;;
    *) echo "Invalid program number. Choose 1 to 15."; exit 1 ;;
esac

echo "====================================================="
echo " Compiling and Running Program $PROG: $CLASS"
echo "====================================================="

rm -rf build *.class *.jar 2>/dev/null
mkdir -p build
javac -classpath `hadoop classpath` -d build src/${CLASS}.java
jar -cvf job.jar -C build/ .

hadoop fs -mkdir -p /input_${PROG}
hadoop fs -rm -r /output_${PROG} 2>/dev/null
hadoop fs -put -f inputs/${IN} /input_${PROG}/

hadoop jar job.jar ${CLASS} /input_${PROG} /output_${PROG}

echo "====================================================="
echo " RESULT FOR PROGRAM $PROG ($CLASS):"
echo "====================================================="
hadoop fs -cat /output_${PROG}/part-r-00000 2>/dev/null || hadoop fs -cat /output_${PROG}/part-m-00000
