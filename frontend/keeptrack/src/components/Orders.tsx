import React, { useState, useEffect } from "react";
import { Formik, Field, Form, ErrorMessage } from "formik";

import { getOpenOrders, assignOrder } from "../services/order.service";
import EventBus from "../common/EventBus";

import SrOrder from "../types/order.type";
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import { TransitionProps } from '@mui/material/transitions';
import { AppBar, Button, Dialog, Divider, IconButton, List, ListItem, ListItemText, Toolbar, Typography } from "@mui/material";
import Slide from '@mui/material/Slide';

import * as AuthService from "../services/auth.service";
import NewOrder from "./NewOrder";

const Transition = React.forwardRef(function Transition(
    props: TransitionProps & {
        children: React.ReactElement;
    },
    ref: React.Ref<unknown>,
) {
    return <Slide direction="up" ref={ref} {...props} />;
});
const Order: React.FC = () => {
    const [content, setContent] = useState<SrOrder[]>([]);
    const [open, setOpen] = React.useState(false);

    useEffect(() => {
        getOpenOrders().then(
            (response) => {
                setContent(response.data)
            },
            (error) => {
                AuthService.logout();
                const _content =
                    (error.response &&
                        error.response.data &&
                        error.response.data.message) ||
                    error.message ||
                    error.toString();

                setContent(_content);


            }
        );
    }, []);

    function getTotal(order: SrOrder) {
        return order.alu + order.baux + order.food + order.helium + order.hydro + order.pluto + order.silicon + order.titan + order.uran + order.water;
    }

    function assignOrderEvt(order: SrOrder) {
        let user = AuthService.getCurrentUser();
        assignOrder(order, user.name);
    }
    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };
    return (
        <div>
            <NewOrder />


            <TableContainer component={Paper} sx={{ maxHeight: 500, minWidth: 1400, align: "left" }} >
                <Table sx={{ overflow: "auto" }} size="small" stickyHeader aria-label="sticky table">
                    <TableHead>
                        <TableRow>
                            <TableCell>Besteller</TableCell>
                            <TableCell align="right">Titan</TableCell>
                            <TableCell align="right">Silicon</TableCell>
                            <TableCell align="right">Helium</TableCell>
                            <TableCell align="right">Food</TableCell>
                            <TableCell align="right">Water</TableCell>
                            <TableCell align="right">Alu</TableCell>
                            <TableCell align="right">Baux</TableCell>
                            <TableCell align="right">Uran</TableCell>
                            <TableCell align="right">Plutonium</TableCell>
                            <TableCell align="right">Hydrogen</TableCell>
                            <TableCell align="right">Total Mats</TableCell>
                            <TableCell align="right">Creds</TableCell>
                            <TableCell align="right">Plani</TableCell>
                            <TableCell align="center">Edit</TableCell>

                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {content.map((order) => (
                            <TableRow key={order.id}>
                                <TableCell component="th" scope="row">
                                    {order.orderedBy.name}
                                </TableCell>
                                <TableCell align="right">{order.titan}</TableCell>
                                <TableCell align="right">{order.silicon}</TableCell>
                                <TableCell align="right">{order.helium}</TableCell>
                                <TableCell align="right">{order.food}</TableCell>
                                <TableCell align="right">{order.water}</TableCell>
                                <TableCell align="right">{order.alu}</TableCell>
                                <TableCell align="right">{order.baux}</TableCell>
                                <TableCell align="right">{order.uran}</TableCell>
                                <TableCell align="right">{order.pluto}</TableCell>
                                <TableCell align="right">{order.hydro}</TableCell>
                                <TableCell align="right">{getTotal(order)}</TableCell>
                                <TableCell align="right">{order.credits}</TableCell>
                                <TableCell align="right">{order.target.x}-{order.target.y}-{order.target.z}</TableCell>
                                <TableCell align="center">
                                    <Button variant="contained"
                                        onClick={() => {
                                            assignOrderEvt(order)
                                        }}
                                    >
                                        Assign</Button>
                                </TableCell>


                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </div>
    )

};

export default Order;