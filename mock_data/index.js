const serviceAccount = require("./dat251-firebase-adminsdk-h77n5-de5d8744ea.json");

const admin = require('firebase-admin');
const data = require("./MOCK_DATA.json");

const collectionKey = "products"; //name of the collection

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount)
});

const firestore = admin.firestore();
const settings = { timestampsInSnapshots: true };

firestore.settings(settings);

if (data && (typeof data === "object")) {

  Object.keys(data).forEach(docKey => {
    firestore.collection(collectionKey).doc().set(data[docKey]).then((res) => {

    }).catch((error) => {
      console.error("Error writing document: ", error);
    });
  });
}
