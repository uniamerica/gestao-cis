import { Fragment, useContext, useEffect } from "react";
import CustomizedTables from "../../Components/Tables/AllUsersTable";
import Container from "@mui/material/Container";
import { useNavigate } from "react-router";
import { AuthContext } from "../../Contexts/authContext";
import Typography from '@mui/material/Typography'

export default function Home() {
  const navigate = useNavigate();
  const { isAuth } = useContext(AuthContext);

  useEffect(() => {
    if (isAuth) {
      return navigate("/home");
    } else {
      return navigate("../");
    }
  }, []);

  return (
    <Fragment>
      <Container maxWidth="lg">
        <Typography variant="h4" color="initial" sx={{margin: '1rem 0'}}>
          Listagem de Agendamentos
        </Typography>
        <CustomizedTables></CustomizedTables>
      </Container>
    </Fragment>
  );
}
