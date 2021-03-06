/*****************************************************************************
*
* Copyright (c) 2000 - 2019, Lawrence Livermore National Security, LLC
* Produced at the Lawrence Livermore National Laboratory
* LLNL-CODE-442911
* All rights reserved.
*
* This file is  part of VisIt. For  details, see https://visit.llnl.gov/.  The
* full copyright notice is contained in the file COPYRIGHT located at the root
* of the VisIt distribution or at http://www.llnl.gov/visit/copyright.html.
*
* Redistribution  and  use  in  source  and  binary  forms,  with  or  without
* modification, are permitted provided that the following conditions are met:
*
*  - Redistributions of  source code must  retain the above  copyright notice,
*    this list of conditions and the disclaimer below.
*  - Redistributions in binary form must reproduce the above copyright notice,
*    this  list of  conditions  and  the  disclaimer (as noted below)  in  the
*    documentation and/or other materials provided with the distribution.
*  - Neither the name of  the LLNS/LLNL nor the names of  its contributors may
*    be used to endorse or promote products derived from this software without
*    specific prior written permission.
*
* THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT  HOLDERS AND CONTRIBUTORS "AS IS"
* AND ANY EXPRESS OR  IMPLIED WARRANTIES, INCLUDING,  BUT NOT  LIMITED TO, THE
* IMPLIED WARRANTIES OF MERCHANTABILITY AND  FITNESS FOR A PARTICULAR  PURPOSE
* ARE  DISCLAIMED. IN  NO EVENT  SHALL LAWRENCE  LIVERMORE NATIONAL  SECURITY,
* LLC, THE  U.S.  DEPARTMENT OF  ENERGY  OR  CONTRIBUTORS BE  LIABLE  FOR  ANY
* DIRECT,  INDIRECT,   INCIDENTAL,   SPECIAL,   EXEMPLARY,  OR   CONSEQUENTIAL
* DAMAGES (INCLUDING, BUT NOT  LIMITED TO, PROCUREMENT OF  SUBSTITUTE GOODS OR
* SERVICES; LOSS OF  USE, DATA, OR PROFITS; OR  BUSINESS INTERRUPTION) HOWEVER
* CAUSED  AND  ON  ANY  THEORY  OF  LIABILITY,  WHETHER  IN  CONTRACT,  STRICT
* LIABILITY, OR TORT  (INCLUDING NEGLIGENCE OR OTHERWISE)  ARISING IN ANY  WAY
* OUT OF THE  USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH
* DAMAGE.
*
*****************************************************************************/

// ************************************************************************* //
//                                   avtR2Fpdf.C                             //
// ************************************************************************* //

#include <avtR2Fpdf.h>

#include <float.h>

#include <avtParallel.h>


// ****************************************************************************
//  Method: avtR2Fpdf constructor
//
//  Programmer: Hank Childs
//  Creation:   August 20, 2010
//
// ****************************************************************************

avtR2Fpdf::avtR2Fpdf(int nb) : avtR2Foperator(nb, 0.)
{
    pdf = new float[nb];
    for (int i = 0 ; i < nb ; i++)
        pdf[i] = 0.;
}


// ****************************************************************************
//  Method: avtR2Fpdf destructor
//
//  Purpose:
//      Defines the destructor.  Note: this should not be inlined in the header
//      because it causes problems for certain compilers.
//
//  Programmer: Hank Childs
//  Creation:   August 20, 2010
//
// ****************************************************************************

avtR2Fpdf::~avtR2Fpdf()
{
    delete [] pdf;
}


// ****************************************************************************
//  Method: avtR2Fpdf::AddData
//
//  Purpose:
//      Adds a single data point to the operator.  Ignores the value of the
//      data and simply increments the pdf.
//
//  Programmer: Hank Childs
//  Creation:   August 20, 2010
//
// ****************************************************************************

void
avtR2Fpdf::AddData(int b, float)
{
    pdf[b]++;
}


// ****************************************************************************
//  Method: avtR2Fpdf::FinalizePass
//
//  Purpose:
//      Finalizes the current pass.  In the case of "pdf", there is only
//      one pass.  So calculate the final pdf and return it.  The caller
//      is responsible for freeing the memory.
//
//  Programmer: Hank Childs
//  Creation:   August 20, 2010
//
// ****************************************************************************

float *
avtR2Fpdf::FinalizePass(int pass)
{
    float *rv = new float[nBins];
    SumFloatArrayAcrossAllProcessors(pdf, rv, nBins);
    double nvals = 0;
    int i;
    for (i = 0 ; i < nBins ; i++)
        nvals += rv[i];
    for (i = 0 ; i < nBins ; i++)
        rv[i] /= nvals;
    return rv;
}


