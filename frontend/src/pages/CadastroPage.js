import React, { useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import "../App.css";

function CadastroPage() {

    const [nome, setNome] = useState("");
    const [usuarios, setUsuarios] = useState([]);

    const carregarUsuarios = async () => {
        const response = await axios.get(
            "http://localhost:8080/usuarios"
        );

        setUsuarios(response.data);
    };

    const cadastrarUsuario = async () => {

        await axios.post(
            "http://localhost:8080/usuarios",
            {
                nome: nome
            }
        );

        setNome("");

        carregarUsuarios();
    };

    return (
        <div className="container">

            <div className="card">

                <h1>Cadastro de Usuários</h1>

                <input
                    placeholder="Nome"
                    value={nome}
                    onChange={(e) => setNome(e.target.value)}
                />

                <button onClick={cadastrarUsuario}>
                    Cadastrar
                </button>

                <button onClick={carregarUsuarios}>
                    Atualizar Lista
                </button>
                <Link to="/usuarios">
                    <button>
                        Ver Usuários
                    </button>
                </Link>

                <Link to="/historico">
                    <button>
                        Ver Histórico
                    </button>
                </Link>

                <Link to="/tarefas">
                    <button>
                        Ver Tarefas
                    </button>
                </Link>

                <ul>
                    {usuarios.map((usuario) => (
                        <li key={usuario.id}>
                            {usuario.id} - {usuario.nome}
                        </li>
                    ))}
                </ul>

            </div>

        </div>
    );
}

export default CadastroPage;