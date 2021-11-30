import { Fragment, useContext, useEffect } from "react";
import CustomizedTables from "../../Components/GenericTable";
import Container from "@mui/material/Container";
import { useNavigate } from "react-router";
import { AuthContext } from "../../Contexts/authContext";

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
        <CustomizedTables></CustomizedTables>
      </Container>
    </Fragment>
  );
}
