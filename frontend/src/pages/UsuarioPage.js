import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import "../App.css";

function UsuarioPage() {

    const [usuarios, setUsuarios] = useState([]);

    useEffect(() => {
        carregarUsuarios();
    }, []);

    const carregarUsuarios = async () => {

        try {

            const response = await axios.get(
                "http://localhost:8090/usuarios"
            );

            console.log("USUARIOS:");
            console.log(response.data);

            setUsuarios(response.data);

        } catch (erro) {

            console.log("ERRO USUARIOS:");
            console.error(erro);

        }
    };

    const editarUsuario = async (
        id,
        nomeAtual
    ) => {

        const novoNome = prompt(
            "Digite o novo nome:",
            nomeAtual
        );

        if (!novoNome) {
            return;
        }

        try {

            await axios.put(
                `http://localhost:8090/usuarios/${id}`,
                {
                    nome: novoNome
                }
            );

            carregarUsuarios();

        } catch (erro) {

            alert(
                "Erro ao editar usuário."
            );

            console.error(erro);
        }
    };


    const excluirUsuario = async (id) => {

        const confirmar = window.confirm(
            "Deseja realmente excluir este usuário?"
        );

        if (!confirmar) {
            return;
        }

        try {

            await axios.delete(
                `http://localhost:8090/usuarios/${id}`
            );

            carregarUsuarios();

        } catch (erro) {

            alert(
                "Erro ao excluir usuário."
            );

            console.error(erro);
        }
    };

    return (
        <div className="container">

            <div className="card">

                <h1>Usuários</h1>

                <button onClick={carregarUsuarios}>
                    Atualizar Lista
                </button>

                <ul>
                    {usuarios.map((usuario) => (

                        <li key={usuario.id}>

                            {usuario.id}
                            {" | "}
                            {usuario.nome}
                            {" "}

                            <button
                                onClick={() =>
                                    editarUsuario(
                                        usuario.id,
                                        usuario.nome
                                    )
                                }
                            >
                                Editar
                            </button>

                            <button
                                onClick={() =>
                                    excluirUsuario(
                                        usuario.id
                                    )
                                }
                            >
                                Excluir
                            </button>

                        </li>

                    ))}
                </ul>

                <Link to="/">
                    <button>
                        Voltar
                    </button>
                </Link>

            </div>

        </div>
    );
}

export default UsuarioPage;