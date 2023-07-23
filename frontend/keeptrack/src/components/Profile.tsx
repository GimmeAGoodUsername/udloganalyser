import React, { useEffect, useState } from "react";
import MenuItem from '@mui/material/MenuItem';

import * as AuthService from "../services/auth.service";
import ISrUser, { Race, Role } from "../types/sruser.type";
import * as userApi from "../services/user.service";
import { FormControl, Select } from "@mui/material";
import PlanetList from "./PlanetList/PlanetList";
import IPlanet from "../types/planet.type";

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
  const [ currentUser, setCurrentUser ] = useState<ISrUser>()
  const [ hasChanged, setHasChanged ] = useState<boolean>(false)
  const [ planetsToRemove, setPlanetsToRemove ] = useState<IPlanet[]>([])

  useEffect(() => {
    const user = AuthService.getCurrentUser();
    if (user) {
      userApi.getUserByName(user.name).then(
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
    const newCurrentUser = { ...currentUser!, [name]: value }
    setCurrentUser(newCurrentUser)
    setHasChanged(true)
  };

  const onAddPlanet = () => {
    const planets = currentUser!.planets!
    planets.push({ planetName: '', x: 0, y: 0, z: 0 })
    onUserChange("planets", planets)
  }

  const onChangePlanet = (savedPlanet: IPlanet, i: number) => {
    const planets = currentUser!.planets!.map((p, j) => i === j ? savedPlanet : p)
    onUserChange("planets", planets)
  }

  const onRemovePlanet = (planetId: IPlanet['id'], i: number) => {
    let planets = currentUser!.planets!
    const planet = planets.find(p => planetId && p.id === planetId)

    if (planet) {
      // Planet is known in BE, mark for removal
      planetsToRemove.push(planet);
      setPlanetsToRemove([...planetsToRemove])
    }
    // Remove by index
    planets.splice(i, 1)
    onUserChange("planets", planets)
  }

  const onUpdateUser = async () => {
    setHasChanged(false)

    // remove planet entries
    await Promise.all(planetsToRemove.map(async p => userApi.deletePlanet(p.id)))
    setPlanetsToRemove([])

    // update user data
    const { data } = await userApi.updateUser(currentUser!)
    setCurrentUser(data)
  }

  return (
    <div style={{ width: 400 }}>
      {currentUser && (
        <>
          <h1>Hallöchen {currentUser.name} </h1>
          <FormControl sx={{ m: 1, width: 1 }}>
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
          </FormControl>
          <FormControl sx={{ m: 1, width: 1 }}>
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
          </FormControl>
          <FormControl sx={{ m: 1, width: 1 }}>
            <label>Planeten</label>
            <PlanetList
              user={currentUser}
              onAddPlanet={onAddPlanet}
              onChangePlanet={onChangePlanet}
              onRemovePlanet={onRemovePlanet}
            />
          </FormControl>
          <FormControl sx={{ m: 1, width: 1 }}>
            <button type="submit" className="btn btn-primary btn-block" onClick={onUpdateUser} disabled={!hasChanged}>
              Profil Speichern
            </button>
          </FormControl>
        </>
      )}
    </div>
  )
};

export default Profile;