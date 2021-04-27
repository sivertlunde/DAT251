import firebase from 'firebase';

var config = {
    apiKey: "AIzaSyACKQCdycUQOvW0iuRe0nmi0wg0fXakCLk",
    authDomain: "dat251.firebaseapp.com",
    projectId: "dat251",
    storageBucket: "dat251.appspot.com",
    messagingSenderId: "375043058577",
    appId: "1:375043058577:web:0b59749814ec68c2bdc830"
  
  }
  
firebase.initializeApp(config);

export default firebase;