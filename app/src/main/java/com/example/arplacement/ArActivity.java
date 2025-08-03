package com.example.arplacement;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.Color;
import com.google.ar.sceneform.rendering.MaterialFactory;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ShapeFactory;
import com.google.ar.sceneform.ux.ArFragment;

public class ArActivity extends AppCompatActivity {

    ArFragment arFragment;
    private AnchorNode placedAnchorNode = null;
    private String selectedDrill = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar);

        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arFragment);

        selectedDrill = getIntent().getStringExtra("Drill_Name");

        arFragment.setOnTapArPlaneListener((HitResult hitResult, Plane plane, MotionEvent motionEvent) -> {
            if (placedAnchorNode != null) {
                arFragment.getArSceneView().getScene().removeChild(placedAnchorNode);
                placedAnchorNode.getAnchor().detach();
                placedAnchorNode.setParent(null);
                placedAnchorNode = null;
            }

            Anchor anchor = hitResult.createAnchor();

            switch (selectedDrill) {
                case "Drill 1":
                    // Red cylinder
                    MaterialFactory.makeOpaqueWithColor(this, new Color(android.graphics.Color.RED))
                            .thenAccept(material -> {
                                ModelRenderable cylinder = ShapeFactory.makeCylinder(
                                        0.05f, 0.2f,
                                        Vector3.zero(), material);
                                placeObject(anchor, cylinder);
                            });
                    break;

                case "Drill 2":
                    // Blue cone (simulated by a thin, tall cylinder)
                    MaterialFactory.makeOpaqueWithColor(this, new Color(android.graphics.Color.BLUE))
                            .thenAccept(material -> {
                                ModelRenderable coneLike = ShapeFactory.makeCylinder(
                                        0.03f, 0.3f,
                                        Vector3.zero(), material);
                                placeObject(anchor, coneLike);
                            });
                    break;

                case "Drill 3":
                    // Green arrow-like shape (cube to simulate arrow base)
                    MaterialFactory.makeOpaqueWithColor(this, new Color(android.graphics.Color.GREEN))
                            .thenAccept(material -> {
                                ModelRenderable arrowLike = ShapeFactory.makeCube(
                                        new Vector3(0.1f, 0.02f, 0.3f),
                                        Vector3.zero(), material);
                                placeObject(anchor, arrowLike);
                            });
                    break;

                default:
                    Toast.makeText(this, "Unknown drill selected", Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }

    private void placeObject(Anchor anchor, ModelRenderable renderable) {
        placedAnchorNode = new AnchorNode(anchor);
        placedAnchorNode.setRenderable(renderable);
        placedAnchorNode.setParent(arFragment.getArSceneView().getScene());
    }
}
