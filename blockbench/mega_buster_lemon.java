// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class mega_buster_lemon extends EntityModel<Entity> {
	private final ModelPart bone;
	public mega_buster_lemon(ModelPart root) {
		this.bone = root.getChild("bone");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create().uv(4, 4).cuboid(-1.5F, -1.5F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 4).cuboid(-1.5F, -3.5F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 4).cuboid(-1.5F, -2.5F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 4).cuboid(-1.5F, -2.5F, 1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 4).cuboid(-0.5F, -2.5F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-2.0F, -3.0F, -0.5F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
		return TexturedModelData.of(modelData, 16, 16);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		bone.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}