package boblovespihonkaimod.relics;

import basemod.abstracts.CustomRelic;
import boblovespihonkaimod.DefaultMod;
import boblovespihonkaimod.powers.HonkaiPower;
import boblovespihonkaimod.util.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class StarterRelic extends CustomRelic
{
	private static final Texture IMG = TextureLoader.getTexture(DefaultMod.makeRelicPath("placeholder_relic.png"));
	private static final Texture OUTLINE = TextureLoader.getTexture(DefaultMod.makeRelicOutlinePath("placeholder_relic.png"));
	public static String ID = DefaultMod.makeID("starterRelic");

	public StarterRelic()
	{
		super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.SOLID);
	}

	@Override
	public String getUpdatedDescription()
	{
		return DESCRIPTIONS[0];
	}

	@Override
	public void onPlayerEndTurn()
	{
		flash();
		addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
		HonkaiPower p = new HonkaiPower(AbstractDungeon.player, AbstractDungeon.player, 1);
		addToTop(new ApplyPowerAction(p.owner, p.owner, p));
	}

	@Override
	public void onVictory()
	{
		AbstractPlayer player = AbstractDungeon.player;
		flash();
		addToTop(new RelicAboveCreatureAction(player, this));
		if (player.hasPower(HonkaiPower.id))
		{
			//			AbstractDungeon.actionManager.addToBottom(new SFXAction("MONSTER_COLLECTOR_DEBUFF"));
			//			AbstractDungeon.actionManager.addToBottom(new VFXAction(new CollectorCurseEffect(player.hb.cX, player.hb.cY), 2.0F));
			player.currentHealth -= Math.min(player.currentHealth - 1, player.getPower(HonkaiPower.id).amount);
			addToTop(new ReducePowerAction(AbstractDungeon.player, AbstractDungeon.player, HonkaiPower.id, 1000));
			player.damage(new DamageInfo(player, 0));
		}
	}
}
