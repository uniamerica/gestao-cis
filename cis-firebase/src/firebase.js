// firebase sdk - software development kit
const { initializeApp, cert } = require("firebase-admin/app");
const { getFirestore } = require("firebase-admin/firestore");

// chaves privadas da sdk do app --> pegar em "configurações do projeto"
const serviceAccount = require("../cis-firebase.json");

initializeApp({
  credential: cert(serviceAccount),
});

// banco de dados nosql do firebase
const db = getFirestore();
// db.settings({ ignoreUndefinedProperties: true });

module.exports = {
  db,
};
