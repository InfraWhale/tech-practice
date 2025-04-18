import React from 'react'
import Button from 'react-bootstrap/Button';
import Container from 'react-bootstrap/Container';
import Form from 'react-bootstrap/Form';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import { Outlet } from 'react-router-dom';
import './AppLayout.style.css'

const AppLayout = () => {
  return (
    <div>
        <Navbar expand="lg">
        <Container fluid>
            <Navbar.Brand href="/">
            <img
                src="/Netflix-Logo.png"
                alt="Netflix"
                height="70"
            />
            </Navbar.Brand>
            <Navbar.Toggle aria-controls="navbarScroll" />
            <Navbar.Collapse id="navbarScroll">
            <Nav
                className="me-auto my-2 my-lg-0"
                style={{ maxHeight: '100px' }}
                navbarScroll
            >
                <Nav.Link href="/">Home</Nav.Link>
                <Nav.Link href="/movies">Movies</Nav.Link>
            </Nav>
            <Form className="d-flex">
                <Form.Control
                type="search"
                placeholder="Search"
                className="me-2"
                aria-label="Search"
                />
                <Button className="netflix-search-btn" variant="danger">Search</Button>
            </Form>
            </Navbar.Collapse>
        </Container>
        </Navbar>
        <div className="content-wrapper">
            <Outlet />
        </div>
    </div>
  )
}

export default AppLayout