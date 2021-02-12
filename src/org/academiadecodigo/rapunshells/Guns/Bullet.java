package org.academiadecodigo.rapunshells.Guns;


import org.academiadecodigo.rapunshells.Enemies.Enemy;
import org.academiadecodigo.rapunshells.Game.Game;
import org.academiadecodigo.rapunshells.Game.Order;
import org.academiadecodigo.rapunshells.Game.Screen1;
import org.academiadecodigo.rapunshells.Player.Player;
import org.academiadecodigo.rapunshells.Window;
import org.academiadecodigo.simplegraphics.graphics.Movable;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

import java.util.LinkedList;
import java.util.List;

public class Bullet implements Movable {

    private int damage;
    private Gun gun;
    private Rectangle bulletVisual;
    private static final String[] bulletOrders = {"bulletMove"};
    private static BulletList bulletList = new BulletList();

    public Bullet(int damage, Gun gun) {
        this.gun = gun;
        this.damage = damage;
        bulletList.add(this);
        bulletVisual = new Rectangle(gun.gunVisual.getX(), gun.gunVisual.getY(), 5, 5);
        bulletVisual.draw();
        Game.orderList.add(new Order(bulletOrders[0], this));

    }

    public void bulletMove() {
        if(this.gun.getPlayer() != null) {
            bulletVisual.translate(Window.getCelSizeX() * 4, 0);
            bulletCollisionDetector(Screen1.getSoldier());
        } else {
            bulletVisual.translate(- Window.getCelSizeX() * 2, 0);
            bulletCollisionDetector1(Screen1.getPlayer());
        }
    }

    public void bulletCollisionDetector(Enemy enemy) {
        if (bulletVisual.getX() >= enemy.getEnemyVisual().getX() && bulletVisual.getX() <= enemy.getEnemyVisual().getX() + enemy.getCharWidth()
                && bulletVisual.getY() >= enemy.getEnemyVisual().getY() && (bulletVisual.getY() <= enemy.getEnemyVisual().getY() + enemy.getCharHeight())) {
            enemy.hit(this);
        }
    }

    public void bulletCollisionDetector1(Player player) {
        if ((bulletVisual.getX() >= player.getPlayerVisual().getX()) && (bulletVisual.getX() <= player.getPlayerVisual().getX() + player.getCharWidth())
                && (bulletVisual.getY() >= player.getPlayerVisual().getY()) && (bulletVisual.getY() <= player.getPlayerVisual().getY() + player.getCharHeight())) {
            player.hit(this);
        }
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public void translate(double v, double v1) {

    }

    public static class BulletList {

        private static final List<Bullet> list = new LinkedList<>();

        public void add(Bullet bullet) {
            list.add(bullet);
        }

        public boolean isEmpty() {
            return list.isEmpty();
        }

        public static List<Bullet> getList() {
            return list;
        }
    }
}
