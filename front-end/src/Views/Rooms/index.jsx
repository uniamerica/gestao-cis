import { Container, Typography } from "@mui/material";
import React from "react";
import RoomsTable from "../../Components/Tables/RoomsTable";

export default function Rooms() {
  return (
    <React.Fragment>
      <Container maxWidth="lg">
        <Typography variant="h4" color="initial" sx={{ margin: "1rem 0" }}>
          Listagem de Salas
        </Typography>
        <Typography variant="h5" color="initial">
          Rooms funciona
        </Typography>
        <RoomsTable />
      </Container>
    </React.Fragment>
  );
}
