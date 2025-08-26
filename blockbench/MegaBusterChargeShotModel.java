// Made with Blockbench 4.12.2
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class MegaBusterChargeShotModel extends EntityModel<MegaBusterChargeShotEntity> {
	private final ModelPart bone;
	public MegaBusterChargeShotModel(ModelPart root) {
		this.bone = root.getChild("bone");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create().uv(58, 8).cuboid(-3.0F, -1.0F, -1.0F, 4.0F, 1.0F, 4.0F, new Dilation(0.0F))
		.uv(20, 33).cuboid(-3.0F, -12.0F, -1.0F, 4.0F, 1.0F, 4.0F, new Dilation(0.0F))
		.uv(40, 41).cuboid(-4.0F, -2.0F, -2.0F, 6.0F, 1.0F, 6.0F, new Dilation(0.0F))
		.uv(60, 13).cuboid(-5.0F, -2.0F, -1.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
		.uv(60, 0).cuboid(2.0F, -2.0F, -1.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
		.uv(24, 64).cuboid(-3.0F, -2.0F, 4.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 64).cuboid(-3.0F, -2.0F, -3.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(22, 40).cuboid(-4.0F, -11.0F, -2.0F, 6.0F, 1.0F, 6.0F, new Dilation(0.0F))
		.uv(0, 5).cuboid(-5.0F, -11.0F, -1.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(2.0F, -11.0F, -1.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
		.uv(52, 9).cuboid(-3.0F, -11.0F, 4.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(52, 7).cuboid(-3.0F, -11.0F, -3.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 33).cuboid(-4.0F, -3.0F, -3.0F, 6.0F, 1.0F, 8.0F, new Dilation(0.0F))
		.uv(6, 59).cuboid(-6.0F, -3.0F, -1.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
		.uv(52, 57).cuboid(-5.0F, -3.0F, -2.0F, 1.0F, 1.0F, 6.0F, new Dilation(0.0F))
		.uv(38, 57).cuboid(2.0F, -3.0F, -2.0F, 1.0F, 1.0F, 6.0F, new Dilation(0.0F))
		.uv(58, 48).cuboid(3.0F, -3.0F, -1.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
		.uv(15, 63).cuboid(-3.0F, -3.0F, 5.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(60, 61).cuboid(-3.0F, -3.0F, -4.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(28, 31).cuboid(-4.0F, -10.0F, -3.0F, 6.0F, 1.0F, 8.0F, new Dilation(0.0F))
		.uv(0, 16).cuboid(-6.0F, -10.0F, -1.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
		.uv(0, 51).cuboid(-5.0F, -10.0F, -2.0F, 1.0F, 1.0F, 6.0F, new Dilation(0.0F))
		.uv(40, 48).cuboid(2.0F, -10.0F, -2.0F, 1.0F, 1.0F, 6.0F, new Dilation(0.0F))
		.uv(0, 11).cuboid(3.0F, -10.0F, -1.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
		.uv(58, 53).cuboid(-3.0F, -10.0F, 5.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(12, 59).cuboid(-3.0F, -8.0F, -5.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(60, 18).cuboid(-3.0F, -8.0F, 6.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 22).cuboid(4.0F, -8.0F, -1.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
		.uv(0, 42).cuboid(3.0F, -8.0F, -3.0F, 1.0F, 1.0F, 8.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-5.0F, -8.0F, -4.0F, 8.0F, 1.0F, 10.0F, new Dilation(0.0F))
		.uv(26, 0).cuboid(-7.0F, -8.0F, -1.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
		.uv(10, 43).cuboid(-6.0F, -8.0F, -3.0F, 1.0F, 1.0F, 8.0F, new Dilation(0.0F))
		.uv(30, 48).cuboid(-6.0F, -7.0F, -3.0F, 1.0F, 1.0F, 8.0F, new Dilation(0.0F))
		.uv(0, 27).cuboid(-7.0F, -7.0F, -1.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
		.uv(0, 11).cuboid(-5.0F, -7.0F, -4.0F, 8.0F, 1.0F, 10.0F, new Dilation(0.0F))
		.uv(20, 47).cuboid(3.0F, -7.0F, -3.0F, 1.0F, 1.0F, 8.0F, new Dilation(0.0F))
		.uv(26, 5).cuboid(4.0F, -7.0F, -1.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
		.uv(60, 24).cuboid(-3.0F, -7.0F, 6.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(60, 22).cuboid(-3.0F, -7.0F, -5.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(48, 48).cuboid(-6.0F, -6.0F, -3.0F, 1.0F, 1.0F, 8.0F, new Dilation(0.0F))
		.uv(46, 57).cuboid(-7.0F, -6.0F, -1.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
		.uv(0, 22).cuboid(-5.0F, -6.0F, -4.0F, 8.0F, 1.0F, 10.0F, new Dilation(0.0F))
		.uv(48, 32).cuboid(3.0F, -6.0F, -3.0F, 1.0F, 1.0F, 8.0F, new Dilation(0.0F))
		.uv(32, 57).cuboid(4.0F, -6.0F, -1.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
		.uv(60, 28).cuboid(-3.0F, -6.0F, 6.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(50, 23).cuboid(-6.0F, -5.0F, -3.0F, 1.0F, 1.0F, 8.0F, new Dilation(0.0F))
		.uv(58, 41).cuboid(-7.0F, -5.0F, -1.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
		.uv(26, 1).cuboid(-5.0F, -5.0F, -4.0F, 8.0F, 1.0F, 10.0F, new Dilation(0.0F))
		.uv(50, 13).cuboid(3.0F, -5.0F, -3.0F, 1.0F, 1.0F, 8.0F, new Dilation(0.0F))
		.uv(8, 52).cuboid(-6.0F, -9.0F, -2.0F, 1.0F, 1.0F, 6.0F, new Dilation(0.0F))
		.uv(26, 12).cuboid(-5.0F, -9.0F, -3.0F, 8.0F, 1.0F, 8.0F, new Dilation(0.0F))
		.uv(52, 0).cuboid(3.0F, -9.0F, -2.0F, 1.0F, 1.0F, 6.0F, new Dilation(0.0F))
		.uv(58, 34).cuboid(-4.0F, -9.0F, 5.0F, 6.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(58, 32).cuboid(-4.0F, -9.0F, -4.0F, 6.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 58).cuboid(4.0F, -5.0F, -1.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
		.uv(60, 59).cuboid(-3.0F, -5.0F, 6.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(60, 57).cuboid(-3.0F, -5.0F, -5.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(60, 26).cuboid(-3.0F, -6.0F, -5.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(58, 36).cuboid(-4.0F, -4.0F, -4.0F, 6.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(58, 38).cuboid(-4.0F, -4.0F, 5.0F, 6.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(16, 56).cuboid(3.0F, -4.0F, -2.0F, 1.0F, 1.0F, 6.0F, new Dilation(0.0F))
		.uv(26, 22).cuboid(-5.0F, -4.0F, -3.0F, 8.0F, 1.0F, 8.0F, new Dilation(0.0F))
		.uv(24, 57).cuboid(-6.0F, -4.0F, -2.0F, 1.0F, 1.0F, 6.0F, new Dilation(0.0F))
		.uv(16, 52).cuboid(-3.0F, -10.0F, -4.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, 24.0F, -1.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}
	@Override
	public void setAngles(MegaBusterChargeShotEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		bone.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}