import React, { useEffect, useState } from "react";
import MenuItem from '@mui/material/MenuItem';

import * as AuthService from "../services/auth.service";
import ISrUser, { Race, Role } from "../types/sruser.type";
import { getUserByName, updateUser } from "../services/user.service";
import { FormControl, Select } from "@mui/material";
import PlanetList from "./PlanetList/PlanetList";

const ITEM_HEIGHT = 48;
const ITEM_PADDING_TOP = 8;
const MenuProps = {
  PaperProps: {
    style: {
      maxHeight: ITEM_HEIGHT * 4.5 + ITEM_PADDING_TOP,
      width: 250,
    },
  },
};

const Profile: React.FC<{}> = () => {
  const [ currentUser, setCurrentUser ] = useState<ISrUser>();

  useEffect(() => {
    const user = AuthService.getCurrentUser();
    if (user) {
      getUserByName(user.name).then(
        (response) => {
          setCurrentUser(response.data);
        },
        (error) => {
          const _content =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();

          setCurrentUser(_content);
        }
      );
    }
  }, []);

  const onUserChange = (name: keyof ISrUser, value: any) => {
    if (!currentUser) {
      return;
    }

    const newCurrentUser = { ...currentUser, [name]: value }

    updateUser(newCurrentUser).then(
      (response) => {
        console.info('updated user', response)
      }
    );
    setCurrentUser(newCurrentUser)
  };

  return (
    <div>
      {currentUser && (
        <>
          <h1>Hallöchen {currentUser.name} </h1>
          <FormControl sx={{ m: 1, width: 300 }}>
            <label htmlFor="raceSelect">Rasse</label>
            <Select
              id="raceSelect"
              defaultValue={Race[`${currentUser.race}`]}
              value={currentUser.race}
              onChange={(e) => onUserChange('race', e.target.value)}
              MenuProps={MenuProps}>
              {
                Object.values(Race).map((key) => (
                  <MenuItem
                    key={key}
                    value={key}>
                    {key}
                  </MenuItem>
                ))}
            </Select>
            <label htmlFor="roleSelect">Rolle</label>
            <Select
              id="roleSelect"
              defaultValue={Role[`${currentUser.role}`]}
              value={currentUser.role}
              onChange={(e) => onUserChange('role', e.target.value)}
              MenuProps={MenuProps}>
              {
                Object.values(Role).map((key) => (
                  <MenuItem
                    key={key}
                    value={key}>
                    {key}
                  </MenuItem>
                ))}
            </Select>
            <label>Planets</label>
            <PlanetList user={currentUser} onUpdatePlanets={(planets => onUserChange("planets", planets))}/>
          </FormControl>
        </>
      )}
    </div>
  )
};

export default Profile;