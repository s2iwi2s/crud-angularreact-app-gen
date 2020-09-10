          <FormControl fullWidth margin="normal">
            <InputLabel shrink id="XYfield-var-tmplYX-label">XYfield-label-tmplYX</InputLabel>
            <Select labelId="XYfield-var-tmplYX-label" placeholder="Enter XYfield-label-tmplYX"
              name="XYfield-var-tmplYX" value={this.state.XYfield-var-tmplYX.id}
              onChange={(e) => this.changeSelectState(e)}>
              {this.renderOptions(this.state.listService.XYfield-var-tmplYXList)}
            </Select>
          </FormControl>