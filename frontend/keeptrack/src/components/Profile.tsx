import React, { useEffect, useState } from "react";
import MenuItem from '@mui/material/MenuItem';

import * as AuthService from "../services/auth.service";
import ISrUser, { Race, Role } from "../types/sruser.type";
import { getUserByName, updateUser } from "../services/user.service";
import { FormControl, Select, SelectChangeEvent } from "@mui/material";
import IPlanet from "../types/planet.type";
import PlanetList from "./PlanetList/PlanetList";

type Props = {}
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
const Profile: React.FC<Props> = () => {
  const [ currentUser, setCurrentUser ] = useState<ISrUser>();
  const [ constRace, selectRace ] = React.useState<Race>();
  const [ role, selectRole ] = React.useState<Role>();
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
  const onUpdatePlanets = (planets: IPlanet[]): void => {
    if (!currentUser) {
      return;
    }

    const newCurrentUser = { ...currentUser, planets: planets }

    updateUser(newCurrentUser).then(
      (response) => {
        console.info('updated user', response)
      }
    );

    setCurrentUser(newCurrentUser)
  }

  const handleRaceChange = (event: SelectChangeEvent) => {
    const {
      target: { value },
    } = event;
    selectRace(Race[value as keyof typeof Race]);
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
              value={constRace}
              onChange={handleRaceChange}
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
              value={role}
              onChange={handleRaceChange}
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
            <PlanetList user={currentUser} onUpdatePlanets={onUpdatePlanets}/>
          </FormControl>
        </>
      )
      }
    </div>
  )
};

export default Profile;