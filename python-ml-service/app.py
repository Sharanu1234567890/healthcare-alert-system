from flask import Flask, request, jsonify
import joblib
import numpy as np
import os

app = Flask(__name__)

model_files = [f for f in os.listdir("ml-models") if f.endswith(".pkl")]
latest_model = sorted(model_files)[-1]
model = joblib.load(f"ml-models/{latest_model}")

@app.route("/predict", methods=["POST"])
def predict():
    data = request.json
    features = np.array([data['heartRate'], data['spo2'], data['bp']]).reshape(1, -1)
    risk = model.predict_proba(features)[0][1]
    return jsonify({"risk_score": float(risk)})

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)
