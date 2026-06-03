INSERT INTO filmes (nome, genero, foto) VALUES
    ('Piratas do Caribe', 'Aventura', 'https://m.media-amazon.com/images/M/MV5BNDhlMzEyNzItMTA5Mi00YWRhLThlNTktYTQyMTA0MDIyNDEyXkEyXkFqcGc@._V1_.jpg'),
    ('La La Land',        'Musical',  'https://m.media-amazon.com/images/M/MV5BMzUzNDM2NzM2MV5BMl5BanBnXkFtZTgwNTM3NTg4OTE@._V1_.jpg'),
    ('Oppenheimer',       'Drama',    'https://m.media-amazon.com/images/M/MV5BN2JkMDc5MGQtZjg3YS00NmFiLWIyZmQtZTJmNTM5MjVmYTQ4XkEyXkFqcGc@._V1_.jpg'),
    ('Barbie',            'Comédia',  'https://m.media-amazon.com/images/M/MV5BYjI3NDU0ZGYtYjA2YS00Y2RlLTgwZDAtYTE2YTM5ZjE1M2JlXkEyXkFqcGc@._V1_.jpg'),
    ('Poor Things',       'Drama',    'https://m.media-amazon.com/images/M/MV5BYWU2MjRjZTYtMjVkMS00MTBjLWFiMTAtYmZlYTk1YjkyMWFkXkEyXkFqcGc@._V1_.jpg')
ON CONFLICT DO NOTHING;

INSERT INTO diretores (nome) VALUES
    ('James Cameron'),
    ('Steven Spielberg'),
    ('Christopher Nolan'),
    ('Greta Gerwig'),
    ('Yorgos Lanthimos')
ON CONFLICT DO NOTHING;
