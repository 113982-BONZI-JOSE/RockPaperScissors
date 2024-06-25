INSERT INTO players (id, user_name, password, email, avatar, last_login, created_at, updated_at)
VALUES (100, 'hmorais', 'Password03#', 'email@email.com', null, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO players (id, user_name, password, email, avatar, last_login, created_at, updated_at)
VALUES (1000000, 'APP', null, null, null, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO games(id, code, name, description, rules)
VALUES (1000000, 'RPS', 'Rock Paper Scissors',
        'Rock, Paper, Scissors is a simple hand game typically played between two individuals. ' ||
        'The game involves each player simultaneously forming one of three shapes with their hand: ' ||
        'a closed fist representing a rock, an open hand with the palm facing downward representing ' ||
        'a sheet of paper, or an extended index and middle finger forming ' ||
        'a V shape to represent scissors.' ||
        'To play this game we will replace the shapes of the hands with letters, ' ||
        'R for rock, P for paper and S for scissors',
        'The objective of the game is to defeat the opponent by selecting a hand shape that defeats their according to a set of predetermined rules: ' ||
        'rock crushed scissors, scissors cuts paper, and paper covers rock. ' ||
        'The outcome of each round is determined by comparing the hand shapes, ' ||
        'and the player with the winning shape scores a point.');

INSERT INTO matches(id, game_id, player1_id, player2_id, created_at, updated_at, status)
VALUES (1000000, 1000000, 100, 1000000, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'STARTED');

INSERT INTO matches(id, game_id, player_id, created_at, updated_at, status)
VALUES (1000001, 1000000, 100, 1000000, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'FINISHED');

INSERT INTO matches(id, game_id, player1_id, player2_id, created_at, updated_at, status)
VALUES (1000002, 1000000, 100, 1000000, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'CANCELED');

INSERT INTO matches_rps(id, number_of_plays, remainder_plays, player1Score, player2Score)
VALUES (1000000, 10, 5, 3, 2);
INSERT INTO matches_rps(id, number_of_plays, remainder_plays, player1Score, player2Score)
VALUES (1000001, 10, 0, 6, 4, 100);
INSERT INTO matches_rps(id, number_of_plays, remainder_plays, player1Score, player2Score)
VALUES (1000002, 10, 5, 3, 2);

INSERT INTO plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES (1000000, 1000000, 'ROCK', 'PAPER', 1000000);
INSERT INTO plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES (1000001, 1000000, 'PAPER', 'ROCK', 100);
INSERT INTO plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES (1000002, 1000000, 'PAPER', 'ROCK', 100);
INSERT INTO plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES (1000003, 1000000, 'ROCK', 'SCISSORS', 100);
INSERT INTO plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES (1000004, 1000000, 'ROCK', 'PAPER', 1000000);


INSERT INTO plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES (1000010, 1000001, 'ROCK', 'PAPER', 1000000);
INSERT INTO plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES (1000011, 1000001, 'PAPER', 'ROCK', 100);
INSERT INTO plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES (1000012, 1000001, 'PAPER', 'ROCK', 100);
INSERT INTO plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES (1000013, 1000001, 'ROCK', 'SCISSORS', 100);
INSERT INTO plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES (1000014, 1000001, 'ROCK', 'PAPER', 1000000);
INSERT INTO plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES (1000015, 1000001, 'PAPER', 'ROCK', 100);
INSERT INTO plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES (1000016, 1000001, 'PAPER', 'ROCK', 100);
INSERT INTO plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES (1000017, 1000001, 'ROCK', 'SCISSORS', 100);
INSERT INTO plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES (1000018, 1000001, 'ROCK', 'PAPER', 1000000);
INSERT INTO plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES (1000019, 1000001, 'ROCK', 'PAPER', 1000000);

INSERT INTO plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES (1000005, 1000002, 'ROCK', 'PAPER', 1000000);
INSERT INTO plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES (1000006, 1000002, 'PAPER', 'ROCK', 100);
INSERT INTO plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES (1000007, 1000002, 'PAPER', 'ROCK', 100);
INSERT INTO plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES (1000008, 1000002, 'ROCK', 'SCISSORS', 100);
INSERT INTO plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES (1000009, 1000002, 'ROCK', 'PAPER', 1000000);
