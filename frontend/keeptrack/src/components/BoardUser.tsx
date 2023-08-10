import React, { useState, useEffect } from "react";

import EventBus from "../common/EventBus";

const BoardUser: React.FC = () => {
  const [content, setContent] = useState<string>("");

 

  return (
    <div className="container">
      <header className="jumbotron">
        <h3>{content}</h3>
        
      </header>
    </div>
  );
};

export default BoardUser;