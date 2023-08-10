import React, { useEffect, useState } from "react";


import * as AuthService from "../services/auth.service";
import ISrUser, { Race, Role } from "../types/sruser.type";
import * as userApi from "../services/user.service";
import * as adminApi from "../services/admin.service";
import Select, { SelectChangeEvent } from '@mui/material/Select';
import { styled } from '@mui/material/styles';
import Paper from '@mui/material/Paper';

import { Field, Formik, Form } from "formik";
import { Button, FormControl, Grid, InputLabel, MenuItem, OutlinedInput } from "@mui/material";

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
const Item = styled(Paper)(({ theme }) => ({
  backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
  ...theme.typography.body2,
  padding: theme.spacing(1),
  textAlign: 'center',
  color: theme.palette.text.secondary,
  border: 1,
}));
const Admin: React.FC<{}> = () => {
  const [currentUser, setCurrentUser] = useState<ISrUser>()
  const [users, setUsers] = useState<ISrUser[]>();
  const [selectedUser] = useState<string>();
  const [userToDelte, setUserToDelete] = useState<ISrUser>()

  const initialValues: {
    username: string;
    password: string;
    role: string;
  } = {
    username: "",
    password: "",
    role: "NONE"
  };
  useEffect(() => {
    const user = AuthService.getCurrentUser();
    debugger
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
    userApi.getAllUsers().then(
      (response) => {
        debugger
        setUsers(response.data);
      }, (error) => {
        const _content =
          (error.response &&
            error.response.data &&
            error.response.data.message) ||
          error.message ||
          error.toString();

        setUsers(_content);
      }
    )
  }, []);



  const registerUser = (formValue: { username: string; password: string, role: string }) => {
    adminApi.createUser(formValue.username, formValue.password, formValue.role);
  }
  const onUserChange = (event: SelectChangeEvent) => {
    const value = event.target.value;
    const user = users?.find(u => u.name === value);
    setUserToDelete(user)
  };

  const deleteUser = () => {
    if (userToDelte && userToDelte.id) {
      adminApi.deleteUser(userToDelte.id);
    }
  }

  return (
    <Grid container spacing={6}>
      <Grid item xs={6}>
        User Anlegen
        <Formik
          onSubmit={registerUser}
          initialValues={initialValues}
        >
          <Form>
            <div className="form-group">
              <label htmlFor="username">Username</label>
              <Field name="username" type="text" className="form-control" />
            </div>

            <div className="form-group">
              <label htmlFor="password">Password</label>
              <Field name="password" type="text" className="form-control" />
            </div>
            <div>
              <label htmlFor="role">Role</label>
              <div role="group" aria-labelledby="my-radio-group">
                <label>
                  <Field type="radio" name="role" value="NONE" />
                  Keine Rechte
                </label>
                <label>
                  <Field type="radio" name="role" value="USER" />
                  Nutzer
                </label>
                <label>
                  <Field type="radio" name="role" value="ADMIN" />
                  Admin
                </label>
              </div>
            </div>

            <div className="form-group">
              <button type="submit" className="btn btn-primary btn-block">

                <span>Anlegen</span>
              </button>
            </div>



          </Form>
        </Formik>
      </Grid>
      <Grid item xs={6}>
        <label style={{ color: 'red' }}>User deaktivieren (nimmt Zugriff aber löscht Nutzer nicht!)</label>

        <FormControl sx={{ my: 1, width: 300 }}>
          <InputLabel id="demo-multiple-name-label">Name</InputLabel>
          <Select
            labelId="demo-multiple-name-label"
            id="demo-multiple-name"
            defaultValue={''}
            value={selectedUser}
            onChange={onUserChange}
            input={<OutlinedInput label="User" />}
            MenuProps={MenuProps}
          >
            {users?.map((usersToDelete) => (
              <MenuItem
                key={usersToDelete.name}
                value={usersToDelete.name}>
                {usersToDelete.name}
              </MenuItem>
            ))}
          </Select>
          <Button onClick={deleteUser} variant="contained">Benutzer deaktivieren</Button>
        </FormControl>
      </Grid>
    </Grid>
  )
};

export default Admin;