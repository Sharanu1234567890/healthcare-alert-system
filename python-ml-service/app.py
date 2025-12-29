from flask import Flask, request, jsonify
import pickle, os
# noinspection PyInterpreter
import numpy as np

app = Flask(__name__)

MODEL_PATH = os.environ.get("MODEL_PATH", "ml-models/logistic_model.pkl")
# create folder if missing
os.makedirs(os.path.dirname(MODEL_PATH), exist_ok=True)

# If no model exists, make a tiny dummy model for local dev
if not os.path.exists(MODEL_PATH):
    from sklearn.linear_model import LogisticRegression
    X = np.array([[70,98,120,80,16],[120,88,160,100,30]])
    y = np.array([0,1])
    m = LogisticRegression()
    m.fit(X, y)
    with open(MODEL_PATH, "wb") as f:
        pickle.dump(m, f)

with open(MODEL_PATH, "rb") as f:
    model = pickle.load(f)

@app.route("/predict", methods=["POST"])
def predict():
    data = request.get_json() or {}
    # simple feature order
    features = [
        data.get("heartRate", 0),
        data.get("spo2", 0),
        data.get("bp_systolic", 0),
        data.get("bp_diastolic", 0),
        data.get("respiratoryRate", 0)
    ]
    X = np.array(features).reshape(1, -1)
    if hasattr(model, "predict_proba"):
        score = float(model.predict_proba(X)[0][1])
    else:
        score = float(model.predict(X)[0])
    return jsonify({"risk_score": score})

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=int(os.environ.get("PORT", 5000)))