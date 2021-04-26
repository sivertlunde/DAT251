import React from 'react';
import StyledFirebaseAuth from 'react-firebaseui/StyledFirebaseAuth';
import firebase from 'firebase';
import auth from 'firebase/firebase-auth';
//import UserService from '../services/UserService';

const uiConfig = {
    // Popup signin flow rather than redirect flow.
    signInFlow: 'popup',
    //Redirecting to this url if successful login
    signInSuccessUrl: '/',
    // We will display email as auth provider.
    signInOptions: [
        firebase.auth.EmailAuthProvider.PROVIDER_ID
    ]
    //Adding callback for successful login
    // callbacks: {
    //     signInSuccessWithAuthResult: (auth, redirectUrl) => {
    //         const isNew = auth.additionalUserInfo.isNewUser;
    //         if (isNew) {
    //             firebase.auth().currentUser.getIdToken(false).then((token) => {
    //                 UserService.postUser(token).then((response) => {
    //                     console.log(response);
    //                 }).catch((error) => {
    //                     console.log(error);
    //                 })
    //             }).catch((error) => {
    //                 console.log(error);
    //             })
    //         }
    //         return true; 
    //     }
    //   }
};

class Login extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            user: null,
            initializing: true
        };
    }
    componentDidMount() {
        this.unregisterAuthObserver = firebase.auth().onAuthStateChanged(
            (_user) => {
                this.setState({ user: _user, initializing: false });
                console.log("user: ", _user);
            }
        );
    }

    componentWillUnmount() {
        this.unregisterAuthObserver();
    }

    render() {
        if (this.state.initializing) {
            return (
                <div></div>
            );
        }
        
        return (
            <div>
                <h1>Log inn til minhandleliste</h1>
                <p>Log inn her:</p>
                <StyledFirebaseAuth uiConfig={uiConfig} firebaseAuth={firebase.auth()} />
            </div>
        );
    }
}



export default Login