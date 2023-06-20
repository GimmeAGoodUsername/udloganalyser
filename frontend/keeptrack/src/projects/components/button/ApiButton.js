import React, { useCallback } from 'react';
import useUserApi  from '../../api/client';

const ApiButton = () => {
    const {allUsers} = useUserApi;
    
    function callApi(){
        console.log("press");
        
    }
    return (<button onClick={callApi}>Api</button>);

}


export default ApiButton;