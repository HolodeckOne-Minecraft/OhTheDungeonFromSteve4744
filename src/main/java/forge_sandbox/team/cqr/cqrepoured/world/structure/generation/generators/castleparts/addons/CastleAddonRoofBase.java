package forge_sandbox.team.cqr.cqrepoured.world.structure.generation.generators.castleparts.addons;

import forge_sandbox.BlockPos;

public abstract class CastleAddonRoofBase implements ICastleAddon {

	final protected BlockPos startPos;
	final protected int sizeX;
	final protected int sizeZ;

	public CastleAddonRoofBase(BlockPos startPos, int sizeX, int sizeZ) {
		this.startPos = startPos;
		this.sizeX = sizeX;
		this.sizeZ = sizeZ;
	}
}
