/* 
 * Copyright (C) 2021 shadow
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package otd.dungeon.dungeonmaze.populator.maze.structure;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import otd.dungeon.dungeonmaze.populator.maze.MazeRoomBlockPopulator;
import otd.dungeon.dungeonmaze.populator.maze.MazeRoomBlockPopulatorArgs;
import otd.dungeon.dungeonmaze.util.SpawnerUtils;
import otd.lib.async.AsyncWorldEditor;
import otd.lib.async.later.smoofy.Spawner_Later;

public class TopTurveRoomPopulator extends MazeRoomBlockPopulator {

	/** General populator constants. */
	private static final int LAYER_MIN = 1;
	private static final int LAYER_MAX = 5;
	private static final float ROOM_CHANCE = .002f;

	public boolean const_room = true;

	@Override
	public boolean getConstRoom() {
		return const_room;
	}

	// TODO: Implement this feature!
	public static final double CHANCE_TOPTURVE_ADDITION_EACH_LEVEL = -0.167; /* to 2 */

	@Override
	public void populateRoom(MazeRoomBlockPopulatorArgs args) {
		final AsyncWorldEditor world = args.getWorld();
		final int chunkx = args.getChunkX(), chunkz = args.getChunkZ();
		final Random rand = args.getRandom();
		final int x = args.getRoomChunkX();
//		final int y = args.getFloorY();
		final int yCeiling = args.getCeilingY();
		final int z = args.getRoomChunkZ();

		world.setChunk(chunkx, chunkz);

//        // Register the current room as constant room
//        //DungeonMaze.instance.registerConstantRoom(world.getName(), chunk.getX(), chunk.getZ(), x, y, z);

		// Hull
		world.setChunkType(x + 3, yCeiling - 2, z + 3, Material.MOSSY_COBBLESTONE);
		world.setChunkType(x + 3, yCeiling - 2, z + 4, Material.MOSSY_COBBLESTONE);
		world.setChunkType(x + 4, yCeiling - 2, z + 3, Material.MOSSY_COBBLESTONE);
		world.setChunkType(x + 4, yCeiling - 2, z + 4, Material.MOSSY_COBBLESTONE);
		world.setChunkType(x + 2, yCeiling - 1, z + 3, Material.NETHERRACK);
		world.setChunkType(x + 2, yCeiling - 1, z + 4, Material.GLASS);
		world.setChunkType(x + 3, yCeiling - 1, z + 2, Material.GLASS);

		switch (rand.nextInt(5)) {
		case 0:
			world.setChunkType(x + 3, yCeiling - 1, z + 3, Material.GOLD_ORE);
			break;
		case 1:
			world.setChunkType(x + 3, yCeiling - 1, z + 3, Material.IRON_ORE);
			break;
		case 2:
			world.setChunkType(x + 3, yCeiling - 1, z + 3, Material.COAL_ORE);
			break;
		case 3:
			world.setChunkType(x + 3, yCeiling - 1, z + 3, Material.LAPIS_ORE);
			break;
		case 4:
			world.setChunkType(x + 3, yCeiling - 1, z + 3, Material.COAL_ORE);
			// Originally diamond, changed to coal because ore2 could be diamond too
			break;
		default:
			world.setChunkType(x + 3, yCeiling - 1, z + 3, Material.COAL_ORE);
		}

		world.setChunkType(x + 3, yCeiling - 1, z + 5, Material.NETHERRACK);
		world.setChunkType(x + 4, yCeiling - 1, z + 2, Material.NETHERRACK);

		switch (rand.nextInt(5)) {
		case 0:
			world.setChunkType(x + 4, yCeiling - 1, z + 4, Material.GOLD_ORE);
			break;
		case 1:
			world.setChunkType(x + 4, yCeiling - 1, z + 4, Material.IRON_ORE);
			break;
		case 2:
			world.setChunkType(x + 4, yCeiling - 1, z + 4, Material.COAL_ORE);
			break;
		case 3:
			world.setChunkType(x + 4, yCeiling - 1, z + 4, Material.LAPIS_ORE);
			break;
		case 4:
			world.setChunkType(x + 4, yCeiling - 1, z + 4, Material.DIAMOND_ORE);
			break;
		default:
			world.setChunkType(x + 4, yCeiling - 1, z + 4, Material.COAL_ORE);
		}

		world.setChunkType(x + 4, yCeiling - 1, z + 5, Material.GLASS);
		world.setChunkType(x + 5, yCeiling - 1, z + 3, Material.GLASS);
		world.setChunkType(x + 5, yCeiling - 1, z + 4, Material.NETHERRACK);

//        // Pig spawner
//        if(Core.getConfigHandler().isMobSpawnerAllowed("Pig")) {
//            // Get the spawner block
//            Block spawnerBlock = chunk.getBlock(x + 3, yCeiling - 1, z + 4);
//
//            // Call the spawner generation event
//            GenerationSpawnerEvent event = new GenerationSpawnerEvent(spawnerBlock, EntityType.PIG, GenerationSpawnerEvent.GenerationSpawnerCause.NORMAL, rand);
//            Bukkit.getServer().getPluginManager().callEvent(event);
//
//            // Apply the generation event
//            event._apply();
//        }

		// Skeleton spawner
		if (SpawnerUtils.isSkeletonAllowed(world.getWorld())) {
			Spawner_Later later = new Spawner_Later(chunkx * 16 + x + 4, yCeiling - 1, chunkz * 16 + z + 3,
					EntityType.SKELETON);
			world.addLater(later);
//			// Get the spawner block
//			Block spawnerBlock = chunk.getBlock(x + 4, yCeiling - 1, z + 3);
//			spawnerBlock.setType(Material.SPAWNER, true);
//			BlockState blockState = spawnerBlock.getState();
//			CreatureSpawner spawner = ((CreatureSpawner) blockState);
//			spawner.setSpawnedType(EntityType.SKELETON);
//			blockState.update();
//			SpawnerDecryAPI.setSpawnerDecry(spawnerBlock, Main.instance, DungeonType.DungeonMaze, true);
		}
	}

	@Override
	public float getRoomChance() {
		return ROOM_CHANCE;
	}

	/**
	 * Get the minimum layer
	 * 
	 * @return Minimum layer
	 */
	@Override
	public int getMinimumLayer() {
		return LAYER_MIN;
	}

	/**
	 * Get the maximum layer
	 * 
	 * @return Maximum layer
	 */
	@Override
	public int getMaximumLayer() {
		return LAYER_MAX;
	}
}