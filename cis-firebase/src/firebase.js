// firebase sdk - software develpoment kit
const {
  initializeApp,
  applicationDefault,
  cert,
} = require("firebase-admin/app");
const {
  getFirestore,
  Timestamp,
  FieldValue,
} = require("firebase-admin/firestore");

// chaves privadas da sdk do app --> pegar em "configurações do projeto"
const serviceAccount = require("../cis-firebase-32478-firebase-adminsdk-2exww-2c307a6eb2.json");

initializeApp({
  credential: cert(serviceAccount),
});

// banco de dados nosql do firebase
const db = getFirestore();

module.exports = {
  db,
};
