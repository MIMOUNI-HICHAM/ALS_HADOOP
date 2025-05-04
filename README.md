# 📊 Factorisation de Matrice ALS avec Hadoop MapReduce

Ce projet implémente la **factorisation de matrice par l’algorithme Alternating Least Squares (ALS)** en **Hadoop MapReduce**. Il factorise une grande matrice `M` en deux matrices de plus petite dimension `W` et `H`, telles que :

M ≈ W × Hᵗ

## 🧠 Objectif

Factoriser une matrice de grande dimension :

- M ∈ ℝ¹⁰⁰⁰⁰ˣ⁵¹²
- W ∈ ℝ¹⁰⁰⁰⁰ˣᵏ
- H ∈ ℝ⁵¹²ˣᵏ

en utilisant l’algorithme ALS en MapReduce. Cette méthode est largement utilisée dans les **systèmes de recommandation** (comme Netflix, Amazon).

---

## 📁 Structure du projet

```

ALS-Matrix-Factorization-Hadoop/
├── README.md
├── matrix.txt                  # Matrice générée en exemple
├── GenerateMatrix.java         # Code Java pour générer la matrice
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
└── build.sh                   # Script de compilation (optionnel)

````

---

## 🛠️ Instructions d’utilisation

### 1. Compiler les classes Java

```bash
bash build.sh
````

Ou manuellement :

```bash
mkdir -p build
javac -classpath $(hadoop classpath) -d build src/*.java src/util/*.java
```

### 2. Générer la matrice M

```bash
javac GenerateMatrix.java
java GenerateMatrix > matrix.txt
```

Puis l’envoyer dans HDFS :

```bash
hdfs dfs -mkdir /user/matrix
hdfs dfs -put matrix.txt /user/matrix/
```

### 3. Lancer les jobs MapReduce

Les étapes sont :

* Étape 1 : Générer W (aléatoire ou optimisé)
* Étape 2 : Estimer H à partir de W et M
* Répéter alternativement

Pour exécuter le driver :

```bash
hadoop jar build ALSDriver input_path output_path
```

---

## 🔁 Itérations ALS

Les étapes alternent :

1. Fixer H, recalculer W
2. Fixer W, recalculer H

Répéter pendant `MAX_ITER` itérations.

---

## 📊 Exemple de sortie

Les résultats sont stockés dans :

```
output_examples/output_w/part-r-00000  # Matrice W (10000 × k)
output_examples/output_h/part-r-00000  # Matrice H (512 × k)
```

Chaque ligne : `index_ligne val1,val2,...,valk`

---

## 🧪 Évaluation

Utiliser un script Python pour reconstruire M ou calculer l’erreur RMSE :

```python
import numpy as np
W = np.loadtxt("output_w.txt")
H = np.loadtxt("output_h.txt")
M_approx = W @ H.T
```

---

## 📌 Auteur

Réalisé dans le cadre d’un projet universitaire en ingénierie Big Data.

**Auteur** : HICHAM
