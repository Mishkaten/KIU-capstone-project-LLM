import pygame
import random

class Snake:
    def __init__(self, x, y, size):
        self.x = x
        self.y = y
        self.size = size
        self.cells = [(x, y)]
        self.direction = 'right'

    def move(self):
        if self.direction == 'right':
            self.x += self.size
        elif self.direction == 'left':
            self.x -= self.size
        elif self.direction == 'up':
            self.y -= self.size
        elif self.direction == 'down':
            self.y += self.size

        self.cells.insert(0, (self.x, self.y))
        self.cells.pop()

    def change_direction(self, new_direction):
        if (self.direction == 'right' and new_direction != 'left') or \
           (self.direction == 'left' and new_direction != 'right') or \
           (self.direction == 'up' and new_direction != 'down') or \
           (self.direction == 'down' and new_direction != 'up'):
            self.direction = new_direction

    def grow(self):
        self.cells.append(self.cells[-1])

    def draw(self, surface):
        for cell in self.cells:
            pygame.draw.rect(surface, (0, 255, 0), (cell[0], cell[1], self.size, self.size))

class Food:
    def __init__(self, x, y, size):
        self.x = x
        self.y = y
        self.size = size

    def draw(self, surface):
        pygame.draw.rect(surface, (255, 0, 0), (self.x, self.y, self.size, self.size))

class App:
    def __init__(self):
        self.running = False
        self.clock = None
        self.screen = None
        self.snake = None
        self.food = None
        self.cell_size = 20
        self.score = 0
        self.high_scores = []

    def run(self):
        self.init()
        while self.running:
            self.update()
            self.render()
        self.cleanUp()

    def init(self):
        self.screen = pygame.display.set_mode((800, 600))
        pygame.display.set_caption("Snake Game")

        self.clock = pygame.time.Clock()
        self.running = True

        self.snake = Snake(400, 300, self.cell_size)
        self.spawn_food()

        self.load_high_scores()

        pygame.font.init()  # Initialize the font module

    def update(self):
        self.events()

        self.snake.move()

        if self.snake.x < 0 or self.snake.x >= 800 or self.snake.y < 0 or self.snake.y >= 600:
            self.game_over()

        for cell in self.snake.cells[1:]:
            if self.snake.x == cell[0] and self.snake.y == cell[1]:
                self.game_over()

        if self.snake.x == self.food.x and self.snake.y == self.food.y:
            self.snake.grow()
            self.score += 1
            self.spawn_food()

    def events(self):
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                self.running = False
            elif event.type == pygame.KEYDOWN:
                if event.key == pygame.K_w or event.key == pygame.K_UP:
                    self.snake.change_direction('up')
                elif event.key == pygame.K_s or event.key == pygame.K_DOWN:
                    self.snake.change_direction('down')
                elif event.key == pygame.K_a or event.key == pygame.K_LEFT:
                    self.snake.change_direction('left')
                elif event.key == pygame.K_d or event.key == pygame.K_RIGHT:
                    self.snake.change_direction('right')

    def render(self):
        self.screen.fill((0, 0, 0))

        self.draw_menu_line()

        self.snake.draw(self.screen)
        self.food.draw(self.screen)

        pygame.display.flip()

        self.clock.tick(10)

    def cleanUp(self):
        self.save_high_scores()

    def spawn_food(self):
        x = random.randint(0, (800 - self.cell_size) // self.cell_size) * self.cell_size
        y = random.randint(0, (600 - self.cell_size) // self.cell_size) * self.cell_size
        self.food = Food(x, y, self.cell_size)

    def game_over(self):
        self.running = False
        self.display_end_screen()

    def draw_menu_line(self):
        pygame.draw.line(self.screen, (255, 255, 255), (0, 40), (800, 40), 2)
        font = pygame.font.Font(None, 36)
        score_text = font.render("Score: " + str(self.score), True, (255, 255, 255))
        self.screen.blit(score_text, (10, 10))

    def display_end_screen(self):
        self.screen.fill((0, 0, 0))
        font = pygame.font.Font(None, 48)
        text = font.render("Game Over", True, (255, 255, 255))
        text_rect = text.get_rect(center=(400, 200))
        self.screen.blit(text, text_rect)

        score_text = font.render("Score: " + str(self.score), True, (255, 255, 255))
        score_rect = score_text.get_rect(center=(400, 300))
        self.screen.blit(score_text, score_rect)

        high_scores_text = font.render("High Scores:", True, (255, 255, 255))
        high_scores_rect = high_scores_text.get_rect(center=(400, 400))
        self.screen.blit(high_scores_text, high_scores_rect)

        for i, score in enumerate(self.high_scores[:5], start=1):
            score_text = font.render(str(i) + ". " + str(score), True, (255, 255, 255))
            score_rect = score_text.get_rect(center=(400, 400 + i * 40))
            self.screen.blit(score_text, score_rect)

        replay_text = font.render("Press Space to Replay", True, (255, 255, 255))
        replay_rect = replay_text.get_rect(center=(400, 550))
        self.screen.blit(replay_text, replay_rect)

        pygame.display.flip()

        while True:
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    pygame.quit()
                    return
                elif event.type == pygame.KEYDOWN:
                    if event.key == pygame.K_SPACE:
                        self.reset_game()
                        return

    def reset_game(self):
        self.score = 0
        self.snake = Snake(400, 300, self.cell_size)
        self.spawn_food()
        self.running = True

    def load_high_scores(self):
        try:
            with open("high_scores.txt", "r") as file:
                self.high_scores = [int(score.strip()) for score in file.readlines()]
        except FileNotFoundError:
            self.high_scores = []

    def save_high_scores(self):
        self.high_scores.append(self.score)
        self.high_scores.sort(reverse=True)
        with open("high_scores.txt", "w") as file:
            for score in self.high_scores[:10]:
                file.write(str(score) + "\n")

if __name__ == "__main__":
    app = App()
    app.run()