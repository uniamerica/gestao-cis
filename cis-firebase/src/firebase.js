// firebase sdk - software develpoment kit
const admin = require("firebase-admin");

// chaves privadas da sdk do app --> pegar em "configurações do projeto"
const serviceAccount = require("../cis-firebase-32478-firebase-adminsdk-2exww-422849d847.json");

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
});

// banco de dados nosql do firebase
const db = admin.firestore();

module.exports = {
  db,
};
