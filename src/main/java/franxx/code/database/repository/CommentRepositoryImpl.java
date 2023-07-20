package franxx.code.database.repository;

import franxx.code.database.ConnectionUtil;
import franxx.code.database.entity.Comment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentRepositoryImpl implements CommentRepository {
    @Override
    public void insert(Comment comment) {
        try (Connection connection = ConnectionUtil.getSource().getConnection()) {
            String sql = "insert into comments(email, comment) values (?, ?);";

            try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, comment.getEmail());
                statement.setString(2, comment.getComment());
                statement.executeUpdate();

                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        comment.setId(resultSet.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Comment findById(Integer id) {
        try (Connection connection = ConnectionUtil.getSource().getConnection()) {
            String sql = "select * from comments where id = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return new Comment(
                                resultSet.getInt("id"),
                                resultSet.getString("email"),
                                resultSet.getString("comment")
                        );
                    } else {
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Comment> findAll() {
        try (Connection connection = ConnectionUtil.getSource().getConnection()) {
            String sql = "select * from comments";

            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(sql)) {
                    List<Comment> commentList = new ArrayList<>();

                    while (resultSet.next()) {
                        commentList.add(new Comment(
                                resultSet.getInt("id"),
                                resultSet.getString("email"),
                                resultSet.getString("comment")
                        ));
                    }

                    return commentList;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Comment> findAllByEmail(String email) {
        try (Connection connection = ConnectionUtil.getSource().getConnection()) {
            String sql = "select * from comments where email = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, email);

                try (ResultSet resultSet = statement.executeQuery()) {
                    List<Comment> commentList = new ArrayList<>();

                    while (resultSet.next()) {
                        commentList.add(new Comment(
                                resultSet.getInt("id"),
                                resultSet.getString("email"),
                                resultSet.getString("comment")
                        ));
                    }

                    return commentList;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
