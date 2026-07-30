# Big Data Analytics - MapReduce Exercises (Assignment 2)

**Course:** Big Data Analytics (23CSE352)  
**Institution:** Amrita Vishwa Vidyapeetham  
**Student Name:** Sheela Akshar Sakhi  
**Roll Number:** CB.SC.U4CSE23547  
**Class / Section:** CSE - F  

---

## 📌 Repository Overview

This repository contains Java MapReduce implementations for **15 Big Data Analytics exercises** executed on Apache Hadoop. It includes complete source codes, sample input datasets, execution helper scripts, and formal submission documentation with terminal execution screenshots.

---

## 📁 Repository Structure

```text
mapreduce_exercises/
├── src/                        # Java MapReduce Source Code Files (15 Programs)
│   ├── StudentAverage.java
│   ├── DepartmentSalary.java
│   ├── StudentAttendance.java
│   ├── EvenOddCount.java
│   ├── MovieRating.java
│   ├── HighestMark.java
│   ├── LongestWord.java
│   ├── LogLevelCount.java
│   ├── MaxAvgTemp.java
│   ├── GPASort.java
│   ├── ReverseLine.java
│   ├── UppercaseLine.java
│   ├── CountDigits.java
│   ├── RemoveDuplicates.java
│   └── SecondHighestSalary.java
├── inputs/                     # Sample Input Datasets for testing
│   ├── input1.txt ... input15.txt
├── docs/                       # Final Assignment Documentation (.docx)
│   └── Assignment2_MapReduce.docx
├── images/                     # Terminal Execution & Cluster Proof Screenshots
│   ├── 1.png ... 15.png
│   ├── proof-1.png
│   └── proof-2.png
├── run.sh                      # Automated Bash script to run any MapReduce program
└── README.md                   # Project documentation
```

---

## 📋 List of MapReduce Exercises

| # | Exercise Application | Class Name | Input Format Example | Key Concept |
|---|---|---|---|---|
| 1 | Calculate Average Marks Per Student | `StudentAverage.java` | `101,Math,80` | Custom aggregation & floating average |
| 2 | Calculate Total Salary Per Department | `DepartmentSalary.java` | `101,HR,30000` | Sum aggregation with Combiner |
| 3 | Calculate Attendance Percentage | `StudentAttendance.java` | `101,Present` | Percentage computation from string states |
| 4 | Count Even and Odd Numbers | `EvenOddCount.java` | `10` | Numeric modulo classification & count |
| 5 | Average Movie Rating | `MovieRating.java` | `MovieA,5` | Grouping key-value pair aggregation |
| 6 | Highest Mark in Each Department | `HighestMark.java` | `CSE,101,85` | Grouped maximum value search |
| 7 | Identify Longest Word | `LongestWord.java` | `Artificial Intelligence` | Global maximum string length tracking |
| 8 | Log Level Frequency Count | `LogLevelCount.java` | `INFO System Started` | Log parsing & frequency count |
| 9 | Max Average Temperature Per City | `MaxAvgTemp.java` | `chennai,2010,30,31,32,33` | Multicolumn extraction & max calculation |
| 10 | Sort Students by GPA | `GPASort.java` | `Dharun,8.6` | MapReduce automatic Shuffle & Sort by key |
| 11 | Reverse Input Lines | `ReverseLine.java` | `Hello Hadoop` | Map-only string manipulation |
| 12 | Convert Text to Uppercase | `UppercaseLine.java` | `Hello Hadoop` | Map-only text formatting |
| 13 | Count Digits per Word | `CountDigits.java` | `ABC123` | Character-level analysis (Map-only) |
| 14 | Remove Duplicate Words | `RemoveDuplicates.java` | `Apple` | Reducer key-uniqueness deduplication |
| 15 | Find Second Highest Salary | `SecondHighestSalary.java` | `Ram,40000` | Global top-N selection algorithm |

---

## ⚙️ Prerequisites & Setup

- **Operating System:** Ubuntu Linux / macOS (with Hadoop installed)
- **Apache Hadoop:** v3.x (Single-node pseudo-distributed or multi-node cluster)
- **Java Development Kit (JDK):** Java 8 / 11 / 17
- **Hadoop Environment Variables:** Ensure `HADOOP_HOME` and `hadoop` executable are available in `$PATH`.

---

## 🚀 Quick Execution Guide

### 1. Clone the Repository
```bash
git clone https://github.com/aksharsakhi/mapreduce_exercises.git
cd mapreduce_exercises
chmod +x run.sh
```

### 2. Start Hadoop Services (if not already running)
```bash
start-all.sh
```

### 3. Run Any Program (1 - 15) using `run.sh`
To execute **Program 1** (Average Marks):
```bash
./run.sh 1
```

To execute **Program 2** (Department Salary):
```bash
./run.sh 2
```

The script automatically:
1. Compiles the Java class against `hadoop classpath`.
2. Packages the build into a runnable JAR (`job.jar`).
3. Creates the input directory on HDFS and uploads the input dataset.
4. Cleans up any existing HDFS output directory.
5. Runs the MapReduce job via YARN.
6. Displays the terminal output directly on screen!

---

## 📄 Submission Documentation
The final formatted assignment submission document is available in Microsoft Word format under the `docs/` folder:
- **[docs/Assignment2_MapReduce.docx](docs/Assignment2_MapReduce.docx)** (Contains logic descriptions, Java code, execution commands, and embedded terminal output screenshots for all 15 programs).
