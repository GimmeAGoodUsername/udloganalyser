import React, { useState, useEffect } from "react";
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import CloseIcon from '@mui/icons-material/Close';
import Slide from '@mui/material/Slide';
import { TransitionProps } from '@mui/material/transitions';
import { ErrorMessage, Field, Formik, Form } from 'formik';
import * as AuthService from "../services/auth.service";
import ISrUser from '../types/sruser.type';
import { getUserByName } from "../services/user.service";
import { DateTimePicker } from "@mui/x-date-pickers";
import dayjs, { Dayjs } from 'dayjs';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { AppBar } from "@mui/material";
import PlanetPicker from "./PlanetPicker";
import IPlanet from "../types/planet.type";
import SrOrder from "../types/order.type";
import { createOrder } from "../services/order.service";

const Transition = React.forwardRef(function Transition(
  props: TransitionProps & {
    children: React.ReactElement;
  },
  ref: React.Ref<unknown>,
) {
  return <Slide direction="up" ref={ref} {...props} />;
});




const NewOrder: React.FC = () => {
  const [open, setOpen] = React.useState(false);
  const [user, setUser] = useState<ISrUser>();
  const [date, setDate] = React.useState<Dayjs | null>(dayjs());
  const [planet, setPlanet] = React.useState<IPlanet>();

  const initialValues: {
    titan: number,
    silicon: number,
    helium: number,
    food: number,
    water: number,
    alu: number,
    baux: number,
    uran: number,
    pluto: number,
    hydro: number,
    credits: number,
    deliveryDate: Date
  } = {
    titan: 0,
    silicon: 0,
    helium: 11,
    food: 0,
    water: 0,
    alu: 0,
    baux: 0,
    uran: 0,
    pluto: 0,
    hydro: 0,
    credits: 0,
    deliveryDate: new Date()
  };
  useEffect(() => {
    const user = AuthService.getCurrentUser();
    if (user) {
      getUserByName(user.name).then(
        (response) => {
          setUser(response.data)
        },
        (error) => {
          const _content =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();

          setUser(_content);


        }
      );
    }

  }, []);

  const updatePlanet = (updatePlanet: IPlanet): void => {

    setPlanet(updatePlanet);
  }

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleOrder = (formValue: { titan: number; silicon: number; helium: number; food: number; water: number; alu: number; baux: number; uran: number; pluto: number; hydro: number; credits: number; deliveryDate: Date }) => {
    let { titan, silicon, helium, food, water, alu, baux, uran, pluto, hydro, credits } = formValue
    const newOrder: SrOrder = {
      titan: titan,
      silicon: silicon,
      helium: helium,
      food: food,
      water: water,
      alu: alu,
      baux: baux,
      uran: uran,
      pluto: pluto,
      hydro: hydro,
      credits: credits,
      deliveryDate: date?.toDate(),
      orderedBy: user,
      status: false,
      target: planet
    } as SrOrder;
    
    createOrder(newOrder).then(
      (response) => {
        setOpen(false);
      },
      (error) => {
        AuthService.logout();
        const _content =
          (error.response &&
            error.response.data &&
            error.response.data.message) ||
          error.message ||
          error.toString();

      }
    );
  };

  return (
    <div>
      <Button variant="outlined" onClick={handleClickOpen}>
        Neue Bestellung
      </Button>
      <Dialog
        fullScreen
        open={open}
        onClose={handleClose}
        TransitionComponent={Transition}
      >
        <AppBar sx={{ position: 'relative' }}>
          <Toolbar>
            <IconButton
              edge="start"
              color="inherit"
              onClick={handleClose}
              aria-label="close"
            >
              <CloseIcon />
            </IconButton>
            <Typography sx={{ ml: 2, flex: 1 }} variant="h6" component="div">
              Neue Bestellung
            </Typography>

          </Toolbar>
        </AppBar>
        <Formik
          initialValues={initialValues}
          onSubmit={handleOrder}
        >
          <Form>
            <table>
              <tr>
                <th>Ress</th>
                <th>Summe</th>
              </tr>
              <tr>
                <td><label htmlFor="titan">Titan</label></td>
                <td><Field name="titan" type="number" className="form-control" />
                  <ErrorMessage
                    name="titan"
                    component="div"
                    className="alert alert-danger"
                  /></td>
              </tr>
              <tr>
                <td><label htmlFor="silicon">Silicon</label></td>
                <td><Field name="silicon" type="number" className="form-control" />
                  <ErrorMessage
                    name="silicon"
                    component="div"
                    className="alert alert-danger"
                  /></td>
              </tr>
              <tr>
                <td><label htmlFor="helium">Helium</label></td>
                <td><Field name="helium" type="number" className="form-control" />
                  <ErrorMessage
                    name="helium"
                    component="div"
                    className="alert alert-danger"
                  /></td>
              </tr>
              <tr>
                <td><label htmlFor="food">Nahrung</label></td>
                <td><Field name="food" type="number" className="form-control" />
                  <ErrorMessage
                    name="food"
                    component="div"
                    className="alert alert-danger"
                  /></td>
              </tr>
              <tr>
                <td><label htmlFor="water">Wasser</label></td>
                <td><Field name="water" type="number" className="form-control" />
                  <ErrorMessage
                    name="water"
                    component="div"
                    className="alert alert-danger"
                  /></td>
              </tr>
              <tr>
                <td><label htmlFor="alu">Alu</label></td>
                <td><Field name="alu" type="number" className="form-control" />
                  <ErrorMessage
                    name="alu"
                    component="div"
                    className="alert alert-danger"
                  /></td>
              </tr>
              <tr>
                <td><label htmlFor="baux">Baux</label></td>
                <td><Field name="baux" type="number" className="form-control" />
                  <ErrorMessage
                    name="baux"
                    component="div"
                    className="alert alert-danger"
                  /></td>
              </tr>
              <tr>
                <td><label htmlFor="uran">Uran</label></td>
                <td><Field name="uran" type="number" className="form-control" />
                  <ErrorMessage
                    name="uran"
                    component="div"
                    className="alert alert-danger"
                  /></td>
              </tr>
              <tr>
                <td><label htmlFor="pluto">Plutonium</label></td>
                <td><Field name="pluto" type="number" className="form-control" />
                  <ErrorMessage
                    name="pluto"
                    component="div"
                    className="alert alert-danger"
                  /></td>
              </tr>
              <tr>
                <td><label htmlFor="hydro">Wasserstoff</label></td>
                <td><Field name="hydro" type="number" className="form-control" />
                  <ErrorMessage
                    name="hydro"
                    component="div"
                    className="alert alert-danger"
                  /></td>
              </tr>
              <tr>
                <td><label htmlFor="credits">Creds</label></td>
                <td><Field name="credits" type="number" className="form-control" />
                  <ErrorMessage
                    name="credits"
                    component="div"
                    className="alert alert-danger"
                  /></td>
              </tr>
              <tr>
                <td><label htmlFor="deliveryDate">Date</label></td>
                <td>
                  <LocalizationProvider dateAdapter={AdapterDayjs} adapterLocale="de">
                    <DateTimePicker label="Lieferdatum" value={date} onChange={(newValue) => setDate(newValue)} />
                  </LocalizationProvider>
                </td>
              </tr>
              <tr>
                <td><label htmlFor="planet">Planet</label></td>
                <td>
                  {user && (
                    <PlanetPicker user={user} updatePlanet={updatePlanet} />
                  )}
                </td>
              </tr>
              <tr>
                <td> <button type="submit" className="btn btn-primary btn-block" >

                  <span>Login</span>
                </button></td>
              </tr>

            </table>
          </Form>

        </Formik>
      </Dialog>
    </div>
  );
}
export default NewOrder;