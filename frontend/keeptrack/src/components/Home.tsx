import React, { useState, useEffect } from "react";

import { getPublicContent } from "../services/user.service";
import * as AuthService from "../services/auth.service";
import ISrUser from "../types/sruser.type";
import * as publicApi from "../services/public.service";
import { Button } from "@mui/material";

const Home: React.FC = () => {
  const [content, setContent] = useState<number>();
  const [currentUser, setCurrentUser] = useState<ISrUser | undefined>(undefined);

  useEffect(() => {
    setCurrentUser(AuthService.getCurrentUser);
    publicApi.getOxiKillDay().then( (response) => {
      setContent(response.data)
    },
    (error) => {
      console.log(error);
      setContent(0);
    });
  }, []);

  const refreshOxi = () => {
    publicApi.refreshOxi();
}

  return (
    <div className="container">
      <header className="jumbotron">
        <h3>Tage seit letztem Oxmox Abschuss: {content}</h3>
        
        {currentUser&&(
          <div>
            <Button onClick={refreshOxi} > Oxi abgeschossen </Button>
          </div>
        )}
      </header>
    </div>
  );
};

export default Home;