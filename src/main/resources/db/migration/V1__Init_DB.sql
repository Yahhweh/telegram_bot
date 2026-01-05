CREATE TABLE IF NOT EXISTS users (
                                     user_id BIGSERIAL PRIMARY KEY,
                                     telegram_id BIGINT UNIQUE NOT NULL,
                                     username VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS users_progress (
                                              id BIGSERIAL PRIMARY KEY,
                                              user_id BIGINT UNIQUE NOT NULL REFERENCES users(telegram_id) ON DELETE CASCADE,
    total_active_days BIGINT DEFAULT 0,
    total_penalties BIGINT DEFAULT 0,
    last_post_date DATE
    );

CREATE TABLE IF NOT EXISTS post_logs (
                                         post_logs_id BIGSERIAL PRIMARY KEY,
                                         user_id BIGINT NOT NULL REFERENCES users(telegram_id) ON DELETE CASCADE,
    post_date DATE NOT NULL DEFAULT CURRENT_DATE,
    CONSTRAINT unique_day_post UNIQUE (user_id, post_date) -- Ключевое ограничение
    );