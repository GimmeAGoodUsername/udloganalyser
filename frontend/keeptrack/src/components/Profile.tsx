import React, { useEffect, useState } from "react";
import { NavigateFunction, useNavigate } from 'react-router-dom';
import { Field, Formik, Form, ErrorMessage } from "formik";
import * as Yup from "yup";
import MenuItem from '@mui/material/MenuItem';

import { login } from "../services/auth.service";
import ISrUser, { Race, Role } from "../types/sruser.type";
import * as AuthService from "../services/auth.service";
import { getUserByName } from "../services/user.service";
import { FormControl, InputLabel, OutlinedInput, Select, SelectChangeEvent } from "@mui/material";
import IPlanet from "../types/planet.type";
import Planets from "./Planets";

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
    const [currentUser, setCurrentUser] = useState<ISrUser>();
    const [constRace, selectRace] = React.useState<Race>();
    const [role, selectRole] = React.useState<Role>();
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
    const updatePlanet = (updatePlanet: IPlanet): void => {

        currentUser?.planets?.push(updatePlanet);
      }


    const handleRaceChange = (event: SelectChangeEvent) => {
        const {
            target: { value },
        } = event;
        debugger
        selectRace(Race[value as keyof typeof Race]);
    };

    return (
        <div><h1>Hallöchen {currentUser?.name} </h1>
            {currentUser && (

                <FormControl sx={{ m: 1, width: 300 }}>
                    <label htmlFor="raceSelect">Rasse</label>
                    <Select
                        id="raceSelect"
                        defaultValue={Race[`${currentUser?.race}`]}
                        value={constRace}
                        onChange={handleRaceChange}
                        MenuProps={MenuProps}>
                        {
                            Object.values(Race).map((key) => (
                                <MenuItem
                                    key={key}
                                    value={key} >
                                    {key}
                                </MenuItem>
                            ))}
                    </Select>
                    <label htmlFor="roleSelect">Rolle</label>
                    <Select
                        id="roleSelect"
                        defaultValue={Role[`${currentUser?.role}`]}
                        value={role}
                        onChange={handleRaceChange}
                        MenuProps={MenuProps}>
                        {
                            Object.values(Role).map((key) => (
                                <MenuItem
                                    key={key}
                                    value={key} >
                                    {key}
                                </MenuItem>
                            ))}
                    </Select>
                    <label>Planets</label>
                    <Planets user={currentUser} updatePlanet={updatePlanet} />
                </FormControl>



            )
            }</div>

    )
};

export default Profile;