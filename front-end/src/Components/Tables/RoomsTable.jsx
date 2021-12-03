import * as React from 'react';
import { styled } from '@mui/material/styles';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';

const StyledTableCell = styled(TableCell)(({ theme }) => ({
  [`&.${tableCellClasses.head}`]: {
    backgroundColor: "#1CA78C",
    color: theme.palette.common.white,
    fontSize: 18,
  },
  [`&.${tableCellClasses.body}`]: {
    fontSize: 14,
    
  },
}));

function createData(clinic, identification, category, occupation, role) {
  return { clinic, identification, category, occupation, role };
}

const rows = [
  createData('CIS-Foz do Iguaçu', "Sala 1", "Fisioterapia", "05", true),
  createData('CIS-Foz do Iguaçu', "Sala 2", "Psicicologia", "02", false),  

];

export default function CustomizedTables() {
  return (
    <TableContainer component={Paper} sx={{ margin:"2rem 0"}}>
      <Table sx={{ minWidth: 700 }} aria-label="customized table">
        <TableHead>
          <TableRow>
            <StyledTableCell>Clinic</StyledTableCell>
            <StyledTableCell align="center">Identificação</StyledTableCell>
            <StyledTableCell align="center">Categoria</StyledTableCell>
            <StyledTableCell align="center">Ocupação Max.</StyledTableCell>
            <StyledTableCell align="center">Disponibilidade</StyledTableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {rows.map((row) => (
            <TableRow key={row.id}>
              <StyledTableCell component="th" scope="row">
                {row.clinic}
              </StyledTableCell>
              <StyledTableCell align="center">{row.identification}</StyledTableCell>
              <StyledTableCell align="center">{row.category}</StyledTableCell>
              <StyledTableCell align="center">{row.occupation}</StyledTableCell>
              <StyledTableCell align="center">{row.role ? "Disponivel" : "Indisponivel"}</StyledTableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}