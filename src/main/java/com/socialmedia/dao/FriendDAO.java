package com.socialmedia.dao;

import com.socialmedia.model.Friend;
import com.socialmedia.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FriendDAO {

    public void addFriend(int user1Id, int user2Id) throws SQLException {
        String sql = "INSERT INTO friends (user1_id, user2_id) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, user1Id);
            stmt.setInt(2, user2Id);
            stmt.executeUpdate();
        }
    }

    public List<String> getFriendNames(int loggedInUserId) throws SQLException {
        List<String> friendNames = new ArrayList<>();

        // الاستعلام ده بيقول للداتا بيز:
        // لو الـ user1 هو أنا، هاتي اسم الـ user2
        // ولو الـ user2 هو أنا، هاتي اسم الـ user1
        String sql = "SELECT u.name FROM users u " +
                "JOIN friends f ON u.id = (CASE WHEN f.user1_id = ? THEN f.user2_id ELSE f.user1_id END) " +
                "WHERE f.user1_id = ? OR f.user2_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, loggedInUserId); // للـ CASE
            stmt.setInt(2, loggedInUserId); // للـ WHERE (طرف أول)
            stmt.setInt(3, loggedInUserId); // للـ WHERE (طرف ثاني)

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                friendNames.add(rs.getString("name"));
            }
        }
        return friendNames;
    }

    public List<Friend> getFriendsByUserId(int userId) throws SQLException {
        List<Friend> friends = new ArrayList<>();
        String sql = "SELECT * FROM friends WHERE user1_id = ? OR user2_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Friend friend = new Friend(
                        rs.getInt("id"),
                        rs.getInt("user1_id"),
                        rs.getInt("user2_id"),
                        rs.getString("created_at")
                );
                friends.add(friend);
            }
        }
        return friends;
    }
}