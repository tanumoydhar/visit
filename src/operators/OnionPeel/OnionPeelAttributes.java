package llnl.visit.operators;

import llnl.visit.AttributeSubject;
import llnl.visit.CommunicationBuffer;
import llnl.visit.Plugin;
import java.lang.Integer;
import java.util.Vector;

// ****************************************************************************
// Class: OnionPeelAttributes
//
// Purpose:
//    Attributes for the onion peel operator
//
// Notes:      Autogenerated by xml2java.
//
// Programmer: xml2java
// Creation:   Wed Jan 19 15:43:17 PST 2005
//
// Modifications:
//   
// ****************************************************************************

public class OnionPeelAttributes extends AttributeSubject implements Plugin
{
    // Enum values
    public final static int NODEFACE_NODE = 0;
    public final static int NODEFACE_FACE = 1;

    public final static int SEEDIDTYPE_SEEDCELL = 0;
    public final static int SEEDIDTYPE_SEEDNODE = 1;


    public OnionPeelAttributes()
    {
        super(8);

        adjacencyType = NODEFACE_NODE;
        useGlobalId = false;
        categoryName = new String("Whole");
        subsetName = new String("Whole");
        index = new Vector();
        index.addElement(new Integer(1));
        logical = false;
        requestedLayer = 0;
        seedType = SEEDIDTYPE_SEEDCELL;
    }

    public OnionPeelAttributes(OnionPeelAttributes obj)
    {
        super(8);

        int i;

        adjacencyType = obj.adjacencyType;
        useGlobalId = obj.useGlobalId;
        categoryName = new String(obj.categoryName);
        subsetName = new String(obj.subsetName);
        index = new Vector();
        for(i = 0; i < obj.index.size(); ++i)
        {
            Integer iv = (Integer)obj.index.elementAt(i);
            index.addElement(new Integer(iv.intValue()));
        }
        logical = obj.logical;
        requestedLayer = obj.requestedLayer;
        seedType = obj.seedType;

        SelectAll();
    }

    public boolean equals(OnionPeelAttributes obj)
    {
        int i;

        // Create the return value
        return ((adjacencyType == obj.adjacencyType) &&
                (useGlobalId == obj.useGlobalId) &&
                (categoryName == obj.categoryName) &&
                (subsetName == obj.subsetName) &&
                (index == obj.index) &&
                (logical == obj.logical) &&
                (requestedLayer == obj.requestedLayer) &&
                (seedType == obj.seedType));
    }

    public String GetName() { return "OnionPeel"; }
    public String GetVersion() { return "1.0"; }

    // Property setting methods
    public void SetAdjacencyType(int adjacencyType_)
    {
        adjacencyType = adjacencyType_;
        Select(0);
    }

    public void SetUseGlobalId(boolean useGlobalId_)
    {
        useGlobalId = useGlobalId_;
        Select(1);
    }

    public void SetCategoryName(String categoryName_)
    {
        categoryName = categoryName_;
        Select(2);
    }

    public void SetSubsetName(String subsetName_)
    {
        subsetName = subsetName_;
        Select(3);
    }

    public void SetIndex(Vector index_)
    {
        index = index_;
        Select(4);
    }

    public void SetLogical(boolean logical_)
    {
        logical = logical_;
        Select(5);
    }

    public void SetRequestedLayer(int requestedLayer_)
    {
        requestedLayer = requestedLayer_;
        Select(6);
    }

    public void SetSeedType(int seedType_)
    {
        seedType = seedType_;
        Select(7);
    }

    // Property getting methods
    public int     GetAdjacencyType() { return adjacencyType; }
    public boolean GetUseGlobalId() { return useGlobalId; }
    public String  GetCategoryName() { return categoryName; }
    public String  GetSubsetName() { return subsetName; }
    public Vector  GetIndex() { return index; }
    public boolean GetLogical() { return logical; }
    public int     GetRequestedLayer() { return requestedLayer; }
    public int     GetSeedType() { return seedType; }

    // Write and read methods.
    public void WriteAtts(CommunicationBuffer buf)
    {
        if(WriteSelect(0, buf))
            buf.WriteInt(adjacencyType);
        if(WriteSelect(1, buf))
            buf.WriteBool(useGlobalId);
        if(WriteSelect(2, buf))
            buf.WriteString(categoryName);
        if(WriteSelect(3, buf))
            buf.WriteString(subsetName);
        if(WriteSelect(4, buf))
            buf.WriteIntVector(index);
        if(WriteSelect(5, buf))
            buf.WriteBool(logical);
        if(WriteSelect(6, buf))
            buf.WriteInt(requestedLayer);
        if(WriteSelect(7, buf))
            buf.WriteInt(seedType);
    }

    public void ReadAtts(int n, CommunicationBuffer buf)
    {
        for(int i = 0; i < n; ++i)
        {
            int index = (int)buf.ReadByte();
            switch(index)
            {
            case 0:
                SetAdjacencyType(buf.ReadInt());
                break;
            case 1:
                SetUseGlobalId(buf.ReadBool());
                break;
            case 2:
                SetCategoryName(buf.ReadString());
                break;
            case 3:
                SetSubsetName(buf.ReadString());
                break;
            case 4:
                SetIndex(buf.ReadIntVector());
                break;
            case 5:
                SetLogical(buf.ReadBool());
                break;
            case 6:
                SetRequestedLayer(buf.ReadInt());
                break;
            case 7:
                SetSeedType(buf.ReadInt());
                break;
            }
        }
    }


    // Attributes
    private int     adjacencyType;
    private boolean useGlobalId;
    private String  categoryName;
    private String  subsetName;
    private Vector  index; // vector of Integer objects
    private boolean logical;
    private int     requestedLayer;
    private int     seedType;
}

