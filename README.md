# 📊 ALS Matrix Factorization with Hadoop MapReduce

This project implements **matrix factorization using the Alternating Least Squares (ALS)** algorithm in **Hadoop MapReduce**. It factorizes a large matrix `M` into two lower-dimensional matrices `W` and `H`, such that:

\[
M \approx W H^\top
\]

## 🧠 Goal

Factorize a large matrix \( M \in \mathbb{R}^{10000 \times 512} \) into:

- \( W \in \mathbb{R}^{10000 \times k} \)
- \( H \in \mathbb{R}^{512 \times k} \)

using ALS in MapReduce. This technique is widely used in **recommendation systems** (e.g. Netflix, Amazon).

---

## 📁 Project Structure

```

ALS-Matrix-Factorization-Hadoop/
├── README.md
├── matrix.txt                  # Example generated matrix
├── GenerateMatrix.java         # Java code to generate matrix
├── src/
│   ├── ALSDriver.java
│   ├── MatrixLineMapper.java
│   ├── WCalculatorReducer.java
│   ├── HCalculatorReducer.java
│   ├── PartialProductMapper.java
│   ├── MatrixOperations.java
│   └── util/
│       ├── MatrixOperations.java
│       └── vector.java
├── output\_examples/
│   ├── output\_h/part-r-00000
│   └── output\_w/part-r-00000
└── build.sh                   # Optional build helper

````

---

## 🛠️ Usage Instructions

### 1. Compile Java classes

```bash
bash build.sh
````

Or manually:

```bash
mkdir -p build
javac -classpath $(hadoop classpath) -d build src/*.java src/util/*.java
```

### 2. Generate the matrix M

```bash
javac GenerateMatrix.java
java GenerateMatrix > matrix.txt
```

Then upload to HDFS:

```bash
hdfs dfs -mkdir /user/matrix
hdfs dfs -put matrix.txt /user/matrix/
```

### 3. Run MapReduce jobs

You will run:

* Job 1: Generate W (random or optimized)
* Job 2: Estimate H from W and M
* Repeat alternatingly

To run the driver:

```bash
hadoop jar build ALSDriver input_path output_path
```

---

## 🔁 ALS Iterations

Alternate the steps:

1. Fix H, recompute W
2. Fix W, recompute H

Repeat for `MAX_ITER` iterations.

---

## 📊 Output Example

Example outputs are stored in:

```
output_examples/output_w/part-r-00000  # Matrix W (10000 x k)
output_examples/output_h/part-r-00000  # Matrix H (512 x k)
```

Each line: `rowIndex val1,val2,...,valk`

---

## 🧪 Evaluation

Use a Python script to evaluate RMSE or reconstruct M:

```python
import numpy as np
W = np.loadtxt("output_w.txt")
H = np.loadtxt("output_h.txt")
M_approx = W @ H.T
```

---

## 📌 Author

Made as part of a university project in Big Data Engineering.
