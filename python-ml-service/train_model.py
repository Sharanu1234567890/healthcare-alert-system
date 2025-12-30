import pandas as pd
import numpy as np
from sklearn.linear_model import LogisticRegression
from sklearn.model_selection import train_test_split
from sklearn.metrics import classification_report
import joblib
import os

# -----------------------------
# 1. Create Synthetic Dataset
# -----------------------------
np.random.seed(42)

data_size = 1000

data = {
    "heartRate": np.random.randint(50, 140, data_size),
    "spo2": np.random.randint(85, 100, data_size),
    "bp": np.random.randint(80, 180, data_size),
}

df = pd.DataFrame(data)

# Risk logic (LABEL CREATION)
# High risk if vitals cross unsafe thresholds
df["risk"] = (
        (df["heartRate"] > 110) |
        (df["spo2"] < 90) |
        (df["bp"] > 160)
).astype(int)

print("Sample Data:")
print(df.head())

# -----------------------------
# 2. Split Features & Labels
# -----------------------------
X = df[["heartRate", "spo2", "bp"]]
y = df["risk"]

X_train, X_test, y_train, y_test = train_test_split(
    X, y, test_size=0.2, random_state=42
)

# -----------------------------
# 3. Train Model
# -----------------------------
model = LogisticRegression()
model.fit(X_train, y_train)

# -----------------------------
# 4. Evaluate Model
# -----------------------------
y_pred = model.predict(X_test)
print("\nModel Evaluation:")
print(classification_report(y_test, y_pred))

# -----------------------------
# 5. Save Model
# -----------------------------
os.makedirs("ml-models", exist_ok=True)
joblib.dump(model, "ml-models/logistic_model_v1.pkl")

print("\n✅ Model trained and saved as ml-models/logistic_model_v1.pkl")
